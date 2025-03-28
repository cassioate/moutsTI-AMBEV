package org.order.adapters.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 7805883099458337846L;

    private UUID id;

    private Long orderId;

    private double totalValue;

    private String status;

    private List<ProductResponse> products;

}
