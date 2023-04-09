package com.w2coding.settlementserver.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class GlobalException extends Exception {

	private final String code;
	private final String message;

}
