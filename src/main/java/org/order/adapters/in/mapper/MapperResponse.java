package org.order.adapters.in.mapper;

import org.order.adapters.dtos.response.OrderResponse;
import org.order.adapters.dtos.response.ProductResponse;
import org.order.core.domain.OrderDomain;
import org.order.core.domain.ProductDomain;

import java.util.List;

public class MapperResponse {

    public static OrderResponse orderDomainToResponse(OrderDomain domain) {
        return OrderResponse
                .builder()
                .id(domain.getId())
                .orderId(domain.getOrderId())
                .totalValue(domain.getTotalValue())
                .status(domain.getStatus())
                .products(productDomainToResponse(domain.getProducts()))
                .build();
    }

    private static List<ProductResponse> productDomainToResponse(List<ProductDomain> domain) {
        return domain.stream()
                .map(value -> ProductResponse
                        .builder()
                        .id(value.getId())
                        .productId(value.getProductId())
                        .name(value.getName())
                        .price(value.getPrice())
                        .quantity(value.getQuantity())
                        .build()
                ).toList();
    }
}
