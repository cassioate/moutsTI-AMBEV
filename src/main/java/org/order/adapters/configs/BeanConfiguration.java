package org.order.adapters.configs;

import org.order.core.ports.failedmessage.FindAllFailedMessageToProcessPort;
import org.order.core.ports.failedmessage.InsertAllFailedMessagePort;
import org.order.core.ports.failedmessage.InsertFailedMessagePort;
import org.order.core.ports.logging.LoggerPort;
import org.order.core.ports.mapper.OrderDomainMapperPort;
import org.order.core.ports.order.ExistsOrderPersistencePort;
import org.order.core.ports.order.FindOrderPersistencePort;
import org.order.core.ports.order.InsertOrderPersistencePort;
import org.order.core.usecase.failedmessage.impl.InsertFailedMessageUseCaseImpl;
import org.order.core.usecase.failedmessage.impl.RetryFailedMessageUseCaseImpl;
import org.order.core.usecase.order.InsertOrderUseCase;
import org.order.core.usecase.order.impl.FindOrderUseCaseImpl;
import org.order.core.usecase.order.impl.InsertOrderUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    InsertOrderUseCaseImpl insertOrderUseCaseImpl(LoggerPort logger, InsertOrderPersistencePort insertOrderPersistencePort, ExistsOrderPersistencePort existsOrderPersistencePort) {
        return new InsertOrderUseCaseImpl(logger, insertOrderPersistencePort, existsOrderPersistencePort);
    }

    @Bean
    RetryFailedMessageUseCaseImpl retryFailedMessageUseCaseImpl(OrderDomainMapperPort orderDomainMapperPort, LoggerPort logger, InsertAllFailedMessagePort insertAllFailedMessagePort, FindAllFailedMessageToProcessPort findAllFailedMessageToProcessPort, InsertOrderUseCase insertOrderUseCase) {
        return new RetryFailedMessageUseCaseImpl(orderDomainMapperPort, logger, insertAllFailedMessagePort, findAllFailedMessageToProcessPort, insertOrderUseCase);
    }

    @Bean
    InsertFailedMessageUseCaseImpl insertFailedMessageUseCaseImpl(LoggerPort logger, InsertFailedMessagePort insertFailedMessagePort) {
        return new InsertFailedMessageUseCaseImpl(logger, insertFailedMessagePort);
    }

    @Bean
    FindOrderUseCaseImpl findOderUseCaseImpl(LoggerPort logger, FindOrderPersistencePort findOrderPersistencePort) {
        return new FindOrderUseCaseImpl(logger, findOrderPersistencePort);
    }

}
