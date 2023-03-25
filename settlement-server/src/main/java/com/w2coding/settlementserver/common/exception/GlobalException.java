package com.w2coding.settlementserver.common.exception;

import com.w2coding.settlementserver.common.exception.enums.ExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class GlobalException extends Exception {

	private final ExceptionCode exceptionCode;

}
