package com.w2coding.settlementserver.settlement.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SettlementStatus {

    REQUESTED(0),

    SETTLED(1),

    APPROVED(2),

    PAYOUT(3),

    ;

    private final Integer value;

}
