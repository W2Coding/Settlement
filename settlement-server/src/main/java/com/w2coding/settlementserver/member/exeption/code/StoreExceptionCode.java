package com.w2coding.settlementserver.member.exeption.code;

import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreExceptionCode implements GlobalExceptionCode {

	;

	private final String code;
	private final String message;

}
