package com.w2coding.settlementserver.member.domain.enums;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {

    USER('U'),
    MANAGER('M'),
    MANAGER_LEADER('L'),
    ADMIN('A')
    ;

    private final Character code;

    public static MemberType of(Character code) {
        return Stream.of(MemberType.values())
            .filter(memberType -> code.equals(memberType.getCode()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no such a code in MemberType"));
    }
}
