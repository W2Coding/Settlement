package com.w2coding.settlementserver.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    ENABLE(1),

    DISABLE(0),

    ;

    private final Integer value;

}
