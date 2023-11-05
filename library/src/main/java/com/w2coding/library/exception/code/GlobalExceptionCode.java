package com.w2coding.library.exception.code;

import io.grpc.Status;

public interface GlobalExceptionCode {

	String getCode();
	String getMessage();
	Status getStatus();

}
