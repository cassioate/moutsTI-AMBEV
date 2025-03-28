package org.order.adapters.in.consumers;

import lombok.AllArgsConstructor;
import org.order.adapters.exceptions.RedisUnavailableException;
import org.order.adapters.out.logging.LoggerAdapter;
import org.order.core.usecase.failedmessage.InsertFailedMessageUseCase;
import org.order.core.usecase.order.InsertOrderUseCase;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.order.adapters.in.mapper.MapperRequest.orderRequestToDomain;

@Component
@AllArgsConstructor
public class OrderConsumer {

    private final LoggerAdapter logger;

    private final InsertOrderUseCase insertOrderUseCase;

    private final InsertFailedMessageUseCase insertFailedMessageUseCase;

    private final StringRedisTemplate redisTemplate;

    private static final String CACHE_KEY_SUFFIX = "_processed";

    @KafkaListener(topics = "${kafka.topic.orders}", groupId = "orderGroup")
    public void consumeOrder(@Payload String message,
                             @Header(KafkaHeaders.OFFSET) String offSet,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        try {
            if (isMessageAlreadyProcessed(messageKey)) {
                logMessageIgnored(messageKey, offSet, partition, topic);
                return;
            }

            insertOrderUseCase.insert(orderRequestToDomain(message));
            saveMessageInRedis(messageKey);
        } catch (RedisUnavailableException redisUnavailableException) {
            logRedisFailure(messageKey, redisUnavailableException);
        } catch (Exception e) {
            logAndProcessingFailure(messageKey, message, offSet, partition, topic, e);
        }
    }

    private boolean isMessageAlreadyProcessed(String messageKey) {
        return redisTemplate.hasKey(messageKey + CACHE_KEY_SUFFIX);
    }

    private void logMessageIgnored(String messageKey, String offSet, Integer partition, String topic) {
        logger.logInfo("Mensagem já processada, ignorando: key {}, offset {}, partition {}, topic {}", messageKey, offSet, partition, topic);
    }

    private void logRedisFailure(String messageKey, RedisUnavailableException redisUnavailableException) {
        logger.logError("Falha ao salvar a chave no Redis, mas continuando o processamento: key {}, error: {}", messageKey, redisUnavailableException.getMessage());
    }

    private void logAndProcessingFailure(String messageKey, String message, String offSet, Integer partition, String topic, Exception e) {
        logger.logError(String.format("Esta mensagem kafka não pode ser processada, key %s, offset: %s, partition: %s, topic: %s | ERROR: %s", messageKey, offSet, partition, topic, e.getMessage()), e);
        insertFailedMessageUseCase.execute(messageKey, message, e);
        saveMessageInRedis(messageKey);
    }

    private void saveMessageInRedis(String messageKey) throws RedisUnavailableException {
        try {
            redisTemplate.opsForValue().set(messageKey + CACHE_KEY_SUFFIX, "", 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.logError(String.format("O REDIS se encontra indisponivel!"), e);
            throw new RedisUnavailableException(e.getMessage());
        }
    }
}
