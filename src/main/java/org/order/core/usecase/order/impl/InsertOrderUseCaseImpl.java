package org.order.core.usecase.order.impl;

import org.order.core.domain.OrderDomain;
import org.order.core.domain.exceptions.AlreadyExistException;
import org.order.core.ports.logging.LoggerPort;
import org.order.core.ports.order.ExistsOrderPersistencePort;
import org.order.core.ports.order.InsertOrderPersistencePort;
import org.order.core.usecase.order.InsertOrderUseCase;

import static org.order.core.domain.enums.StatusEnum.CALCULADO;

public class InsertOrderUseCaseImpl implements InsertOrderUseCase {

    private final LoggerPort logger;

    private final InsertOrderPersistencePort insertOrderPersistencePort;

    private final ExistsOrderPersistencePort existsOrderPersistencePort;

    public InsertOrderUseCaseImpl(LoggerPort logger, InsertOrderPersistencePort insertOrderPersistencePort, ExistsOrderPersistencePort existsOrderPersistencePort) {
        this.logger = logger;
        this.insertOrderPersistencePort = insertOrderPersistencePort;
        this.existsOrderPersistencePort = existsOrderPersistencePort;
    }

    @Override
    public void insert(OrderDomain orderDomain) {
        logger.logInfo("[INSERT - Order] - Iniciando inserção de um order de id: {}.", orderDomain.getOrderId());
        if (isDuplicate(orderDomain)) {
            logger.logError(String.format("[INSERT - Order] - Já existe um order_id %s salvo no banco!", orderDomain.getOrderId()));
            throw new AlreadyExistException(String.format("Already exist order_id %s in the database!", orderDomain.getOrderId().toString()));
        }

        double totalValue = orderDomain.getProducts().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        orderDomain.setTotalValue(totalValue);
        orderDomain.setStatus(CALCULADO.getStatus());
        insertOrderPersistencePort.insert(orderDomain);
        logger.logInfo(String.format("[INSERT - Order] - OrderId %s inserido no database com sucesso!", orderDomain.getOrderId()));
    }

    private boolean isDuplicate(OrderDomain orderDomain) {
        return existsOrderPersistencePort.exists(orderDomain.getOrderId());
    }

}
