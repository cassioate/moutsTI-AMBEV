package org.order.adapters.out.persistence.repositories.jpa.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.order.adapters.out.persistence.entities.OrderEntity;
import org.order.adapters.out.persistence.mapper.MapperEntity;
import org.order.adapters.out.persistence.repositories.jpa.OrderRepository;
import org.order.core.domain.OrderDomain;
import org.order.core.ports.order.ExistsOrderPersistencePort;
import org.order.core.ports.order.FindOrderPersistencePort;
import org.order.core.ports.order.InsertOrderPersistencePort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderJpaRepositoryImpl implements InsertOrderPersistencePort, ExistsOrderPersistencePort, FindOrderPersistencePort {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void insert (OrderDomain orderDomain) {
        OrderEntity orderEntity = MapperEntity.orderDomainToEntity(orderDomain);
        orderRepository.save(orderEntity);
    }

    @Override
    public boolean exists (Long orderId) {
        return orderRepository.existsByOrderId(orderId);
    }

    @Override
    @Cacheable(value = "get-orders", key = "#orderId", unless = "#result == null")
    public Optional<OrderDomain> find (Long orderId) {
        Optional<OrderEntity> orderEntity = orderRepository.findByOrderId(orderId);
        Optional<OrderDomain> orderDomain = MapperEntity.orderEntityToDomain(orderEntity);
        return orderDomain;
    }

}
