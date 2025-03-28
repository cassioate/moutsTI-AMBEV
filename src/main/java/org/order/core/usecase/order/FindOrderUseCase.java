package org.order.core.usecase.order;

import org.order.core.domain.OrderDomain;

public interface FindOrderUseCase {

    OrderDomain find (Long orderId);

}
