package com.w2coding.settlementserver.member.domain.enums.converter;

import com.w2coding.settlementserver.member.domain.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) {
            throw new RuntimeException("status is null");
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Integer value) {
        if (value == null) {
            throw new RuntimeException("status is null");
        }

        return Stream.of(Status.values())
                .filter(status -> value.equals(status.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such value in Status"));
    }

}
