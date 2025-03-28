package org.order.adapters.out.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.order.core.domain.OrderDomain;
import org.order.core.ports.mapper.OrderDomainMapperPort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MapperDomain implements OrderDomainMapperPort {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDomain mapperStringToOrderDomain(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, OrderDomain.class);
    }
}
