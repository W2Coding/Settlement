package com.w2coding.settlementserver.settlement.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    CARD('C'),
    MOBILE('M'),
    POINT('P'),
    COUPON('T'),
    SHOPKEEPER_COUPON('S'),

    ;

    private final Character code;
}
