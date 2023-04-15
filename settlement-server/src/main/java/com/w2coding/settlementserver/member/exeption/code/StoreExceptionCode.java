package com.w2coding.settlementserver.member.exeption.code;

import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreExceptionCode implements GlobalExceptionCode {
	STORE_NOT_FOUND("S_S001", "Store not found"),
	;

	private final String code;
	private final String message;

}
