package org.order.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -317402588730617997L;

    private UUID id;

    private Long orderId;

    private double totalValue;

    private String status;

    private List<ProductDomain> products;

    public OrderDomain(UUID id, Long orderId, double totalValue, String status, List<ProductDomain> products) {
        this.id = id;
        this.orderId = orderId;
        this.totalValue = totalValue;
        this.status = status;
        this.products = products;
    }

    public OrderDomain(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDomain> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDomain> products) {
        this.products = products;
    }
}
