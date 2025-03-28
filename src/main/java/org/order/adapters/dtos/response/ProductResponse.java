package org.order.adapters.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -6557641003334188754L;

    private UUID id;

    private Long productId;

    private String name;

    private double price;

    private int quantity;

}
