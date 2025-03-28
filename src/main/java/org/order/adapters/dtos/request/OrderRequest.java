package org.order.adapters.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6407856986717409027L;

    private Long orderId;

    private double totalValue;

    private List<ProductRequest> products;

}
