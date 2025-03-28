package org.order.adapters.in.controllers;

import lombok.AllArgsConstructor;
import org.order.adapters.dtos.response.OrderResponse;
import org.order.adapters.in.mapper.MapperResponse;
import org.order.core.domain.OrderDomain;
import org.order.core.usecase.order.FindOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final FindOrderUseCase findOrderUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("id") Long orderId) {
        OrderDomain result = findOrderUseCase.find(orderId);
        return ResponseEntity
                .ok(MapperResponse.orderDomainToResponse(result));
    }

}
