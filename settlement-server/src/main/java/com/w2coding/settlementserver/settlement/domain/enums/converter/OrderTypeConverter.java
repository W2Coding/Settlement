package com.w2coding.settlementserver.settlement.domain.enums.converter;

import com.w2coding.settlementserver.settlement.domain.enums.OrderType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderTypeConverter implements AttributeConverter<OrderType, Character> {

    @Override
    public Character convertToDatabaseColumn(OrderType orderType) {
        if (orderType == null)
            throw new RuntimeException("order type is null");

        return orderType.getCode();
    }

    @Override
    public OrderType convertToEntityAttribute(Character code) {
        if (code == null)
            throw new RuntimeException("code is null");

        return Stream.of(OrderType.values())
                .filter(memberType -> code.equals(memberType.getCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such a code in MemberType"));
    }
}