package org.order.adapters.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6550615573218797545L;

    private Long productId;

    private String name;

    private double price;

    private int quantity;

}
