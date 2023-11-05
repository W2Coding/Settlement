package com.w2coding.securityserver.common.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityStatus {

    ENABLE(1),

    DISABLE(0),

    ;

    private final Integer value;

}
