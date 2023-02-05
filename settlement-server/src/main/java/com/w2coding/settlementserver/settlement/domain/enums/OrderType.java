package com.w2coding.settlementserver.settlement.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    USER('U'),
    MANAGER('M'),

    ;

    private final Character code;

}
