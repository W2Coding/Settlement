package com.w2coding.securityserver.common.domain.enums.converter;

import com.w2coding.securityserver.common.domain.enums.EntityStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EntityStatusConverter implements AttributeConverter<EntityStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EntityStatus entityStatus) {
        if (entityStatus == null) {
            throw new RuntimeException("status is null");
        }

        return entityStatus.getValue();
    }

    @Override
    public EntityStatus convertToEntityAttribute(Integer value) {
        if (value == null) {
            throw new RuntimeException("status is null");
        }

        return Stream.of(EntityStatus.values())
                .filter(status -> value.equals(status.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such value in Status"));
    }

}
