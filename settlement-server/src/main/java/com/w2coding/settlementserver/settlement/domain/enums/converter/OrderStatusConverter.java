package com.w2coding.settlementserver.settlement.domain.enums.converter;

import com.w2coding.settlementserver.settlement.domain.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null)
            throw new RuntimeException("order status is null");

        return orderStatus.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        if (value == null)
            throw new RuntimeException("value is null");

        return Stream.of(OrderStatus.values())
                .filter(memberType -> value.equals(memberType.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such a value in OrderStatus"));
    }
}