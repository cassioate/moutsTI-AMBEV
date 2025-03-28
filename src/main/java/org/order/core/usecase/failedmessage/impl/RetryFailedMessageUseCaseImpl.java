package org.order.core.usecase.failedmessage.impl;

import org.order.core.domain.FailedMessageDomain;
import org.order.core.ports.failedmessage.FindAllFailedMessageToProcessPort;
import org.order.core.ports.failedmessage.InsertAllFailedMessagePort;
import org.order.core.ports.logging.LoggerPort;
import org.order.core.ports.mapper.OrderDomainMapperPort;
import org.order.core.usecase.failedmessage.RetryFailedMessageUseCase;
import org.order.core.usecase.order.InsertOrderUseCase;

import java.time.LocalDateTime;
import java.util.List;

public class RetryFailedMessageUseCaseImpl implements RetryFailedMessageUseCase {

    private final LoggerPort logger;

    private final OrderDomainMapperPort orderDomainMapperPort;

    private final InsertAllFailedMessagePort insertFailedMessagePort;

    private final FindAllFailedMessageToProcessPort findFailedMessagePort;

    private final InsertOrderUseCase insertOrderUseCase;

    public RetryFailedMessageUseCaseImpl(OrderDomainMapperPort orderDomainMapperPort, LoggerPort logger, InsertAllFailedMessagePort insertFailedMessagePort, FindAllFailedMessageToProcessPort findFailedMessagePort, InsertOrderUseCase insertOrderUseCase) {
        this.orderDomainMapperPort = orderDomainMapperPort;
        this.logger = logger;
        this.insertFailedMessagePort = insertFailedMessagePort;
        this.findFailedMessagePort = findFailedMessagePort;
        this.insertOrderUseCase = insertOrderUseCase;
    }

    @Override
    public void execute() {
        logger.logInfo("[RETRY - INICIO] - Executando pedidos represados.");
        List<FailedMessageDomain> failedMessageDomainList = findFailedMessagePort.findAllToProcess();

        List<FailedMessageDomain> newfailedMessageDomainList = failedMessageDomainList.stream()
                .map(value -> sendOrder(value))
                .toList();

        insertFailedMessagePort.insertAll(newfailedMessageDomainList);
        logger.logInfo("[RETRY - FIM] - executado com sucesso!");
    }

    private FailedMessageDomain sendOrder(FailedMessageDomain value) {
        value.setAttemptCount(value.getAttemptCount() + 1);
        value.setLastAttempt(LocalDateTime.now());

        try {
            insertOrderUseCase.insert(orderDomainMapperPort.mapperStringToOrderDomain(value.getMessage()));
            value.setProcessedSuccessfully(true);
            logger.logInfo(String.format("[RETRY - PROCESSANDO] - mensagem de key %s inserida com sucesso!", value.getMessageKey()));
            return value;
        } catch (Exception e) {
            value.setError(e.getMessage());
            value.setProcessedSuccessfully(false);
            logger.logError("[RETRY - ERROR] Erro ao reprocessar a mensagem com key: " + value.getMessageKey(), e);
            return value;
        }
    }

}
