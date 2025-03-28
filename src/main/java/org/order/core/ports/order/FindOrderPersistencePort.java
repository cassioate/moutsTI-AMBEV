package org.order.core.ports.order;

import org.order.adapters.out.persistence.entities.OrderEntity;
import org.order.core.domain.OrderDomain;

import java.util.Optional;

public interface FindOrderPersistencePort {

    Optional<OrderDomain> find (Long orderId);

}
