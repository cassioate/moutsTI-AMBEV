package org.order.core.usecase.order;

import org.order.core.domain.OrderDomain;

public interface InsertOrderUseCase {

    void insert (OrderDomain orderDomain);

}
