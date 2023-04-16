package com.w2coding.settlementserver.common.exception.code;

import io.grpc.Status;

public interface GlobalExceptionCode {

	String getCode();
	String getMessage();
	Status getStatus();

}
