package org.order.adapters.in.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.order.adapters.dtos.request.OrderRequest;
import org.order.adapters.dtos.request.ProductRequest;
import org.order.core.domain.OrderDomain;
import org.order.core.domain.ProductDomain;

import java.util.List;

public class MapperRequest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static OrderDomain orderRequestToDomain(String message) throws JsonProcessingException {
        OrderRequest request = objectMapper.readValue(message, OrderRequest.class);

        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setOrderId(request.getOrderId());
        orderDomain.setTotalValue(request.getTotalValue());
        orderDomain.setProducts(productRequestToDomain(request.getProducts()));
        return orderDomain;
    }

    private static List<ProductDomain> productRequestToDomain(List<ProductRequest> dto) {
        return dto.stream()
                .map(value -> new ProductDomain(
                                null,
                                value.getProductId(),
                                value.getName(),
                                value.getPrice(),
                                value.getQuantity()
                        )
                ).toList();
    }
}
