package org.order.adapters.out.persistence.mapper;

import org.order.adapters.out.persistence.entities.OrderEntity;
import org.order.adapters.out.persistence.entities.ProductEntity;
import org.order.core.domain.OrderDomain;
import org.order.core.domain.ProductDomain;

import java.util.List;
import java.util.Optional;

public class MapperEntity {

    public static OrderEntity orderDomainToEntity(OrderDomain orderDomain) {
        OrderEntity entity = OrderEntity
                .builder()
                .orderId(orderDomain.getOrderId())
                .status(orderDomain.getStatus())
                .totalValue(orderDomain.getTotalValue())
                .build();
        entity.setProducts(productDomainToEntity(orderDomain.getProducts(), entity));

        return entity;
    }

    private static List<ProductEntity> productDomainToEntity(List<ProductDomain> productDomain, OrderEntity entity) {
        return productDomain.stream()
                .map(value -> ProductEntity
                        .builder()
                        .productId(value.getProductId())
                        .name(value.getName())
                        .price(value.getPrice())
                        .quantity(value.getQuantity())
                        .order(entity)
                        .build()
                ).toList();
    }

    public static Optional<OrderDomain> orderEntityToDomain(Optional<OrderEntity> optionalOrderEntity) {
        if (optionalOrderEntity.isEmpty()) {
            return Optional.empty();
        }

        OrderEntity entity = optionalOrderEntity.get();
        OrderDomain domain = new OrderDomain(
                entity.getId(),
                entity.getOrderId(),
                entity.getTotalValue(),
                entity.getStatus(),
                productEntityToDomain(entity.getProducts())
        );

        return Optional.of(domain);
    }

    private static List<ProductDomain> productEntityToDomain(List<ProductEntity> entities) {
        return entities.stream()
                .map(value -> new ProductDomain(
                        value.getId(),
                        value.getProductId(),
                        value.getName(),
                        value.getPrice(),
                        value.getQuantity()
                )).toList();
    }
}
