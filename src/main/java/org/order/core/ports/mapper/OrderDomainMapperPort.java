package org.order.core.ports.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.order.core.domain.OrderDomain;

public interface OrderDomainMapperPort {

    OrderDomain mapperStringToOrderDomain (String value) throws JsonProcessingException;

}
