package com.w2coding.settlementserver.common.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
	SNOWFLAKE_ID_GENERATE_ERROR("S_ID001", "ID 생성에 실패하였습니다"),
	;

	private final String code;
	private final String message;
}
