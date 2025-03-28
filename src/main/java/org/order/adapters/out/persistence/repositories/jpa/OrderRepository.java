package org.order.adapters.out.persistence.repositories.jpa;

import org.order.adapters.out.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByOrderId(Long orderId);

    Optional<OrderEntity> findByOrderId(Long orderId);

}
