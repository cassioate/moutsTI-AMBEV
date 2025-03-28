package org.order.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class ProductDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 8445223539544584513L;

    private UUID id;

    private Long productId;

    private String name;

    private double price;

    private int quantity;

    public ProductDomain(UUID id, Long productId, String name, double price, int quantity) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDomain() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
