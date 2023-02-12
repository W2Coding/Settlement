package com.w2coding.settlementserver.settlement.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SettlementStatus {

    SETTLED(1),

    NOT_YET(0),

    ;

    private final Integer value;

}
