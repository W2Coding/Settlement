package com.w2coding.settlementserver.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {

    BASIC('B'),
    MANAGER('M'),
    MANAGER_LEADER('L'),
    ADMIN('A')
    ;

    private final Character code;
}
