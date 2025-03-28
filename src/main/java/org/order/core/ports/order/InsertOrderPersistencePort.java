package org.order.core.ports.order;

import org.order.core.domain.OrderDomain;

public interface InsertOrderPersistencePort {
    void insert (OrderDomain orderDomain);
}
