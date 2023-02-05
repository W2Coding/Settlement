package com.w2coding.settlementserver.member.domain.enums.converter;

import com.w2coding.settlementserver.member.domain.enums.MemberType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MemberTypeConverter implements AttributeConverter<MemberType, Character> {

    @Override
    public Character convertToDatabaseColumn(MemberType memberType) {
        if (memberType == null)
            throw new RuntimeException("member type is null");

        return memberType.getCode();
    }

    @Override
    public MemberType convertToEntityAttribute(Character code) {
        if (code == null)
            throw new RuntimeException("code is null");

        return Stream.of(MemberType.values())
                .filter(memberType -> code.equals(memberType.getCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such a code in MemberType"));
    }
}
