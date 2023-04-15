package com.w2coding.settlementserver.common.exception;

import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class GlobalException extends RuntimeException {

	private final GlobalExceptionCode exceptionCode;

}
