package com.w2coding.settlementserver.settlement.domain.enums.converter;

import com.w2coding.settlementserver.settlement.domain.enums.PaymentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Character> {

    @Override
    public Character convertToDatabaseColumn(PaymentType paymentType) {
        if (paymentType == null)
            throw new RuntimeException("payment type is null");

        return paymentType.getCode();
    }

    @Override
    public PaymentType convertToEntityAttribute(Character code) {
        if (code == null)
            throw new RuntimeException("code is null");

        return Stream.of(PaymentType.values())
                .filter(memberType -> code.equals(memberType.getCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such a code in MemberType"));
    }
}