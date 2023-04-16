package com.w2coding.settlementserver.common.exception;

import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;
import io.grpc.Status;
import lombok.Getter;

@Getter
public abstract class GlobalException extends RuntimeException {

	private final String code;
	private final String message;
	private final Status status;

	public GlobalException(GlobalExceptionCode exceptionCode) {
		code = exceptionCode.getCode();
		message = exceptionCode.getMessage();
		status = exceptionCode.getStatus();
	}

}
