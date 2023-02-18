package com.w2coding.settlementserver.settlement.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    CANCELED(0),
    PROGRESSING(1),
    COMPLETED(2),

    ;

    private final Integer value;

}
