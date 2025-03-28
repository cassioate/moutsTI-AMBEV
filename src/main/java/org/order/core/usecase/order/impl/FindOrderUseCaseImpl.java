package org.order.core.usecase.order.impl;

import org.order.adapters.out.persistence.entities.OrderEntity;
import org.order.core.domain.OrderDomain;
import org.order.core.domain.exceptions.OrderIdNotExistException;
import org.order.core.ports.logging.LoggerPort;
import org.order.core.ports.order.FindOrderPersistencePort;
import org.order.core.usecase.order.FindOrderUseCase;

import java.util.Optional;

public class FindOrderUseCaseImpl implements FindOrderUseCase {

    private final LoggerPort logger;

    private final FindOrderPersistencePort findOrderPersistencePort;

    public FindOrderUseCaseImpl(LoggerPort logger, FindOrderPersistencePort findOrderPersistencePort) {
        this.logger = logger;
        this.findOrderPersistencePort = findOrderPersistencePort;
    }

    @Override
    public OrderDomain find(Long orderId) {
        logger.logInfo("[FIND - Order] - Buscando orderId {} no database!", orderId);

        Optional<OrderDomain> result = findOrderPersistencePort.find(orderId);
        if (result.isEmpty()) {
            logger.logInfo("[FIND - Order] - Não foi encontrado o orderId {} no database!", orderId);
            throw new OrderIdNotExistException(String.format("OrderId %s não encontrado no database, o orderID pode ainda estar em processamento ou então ele ainda não foi recebido por essa aplicação!", orderId));
        }

        logger.logInfo("[FIND - Order] - Encontrado orderId {} no database!", orderId);
        return result.get();
    }

}
