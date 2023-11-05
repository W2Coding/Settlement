package com.w2coding.securityserver.member.exeption.code;

import com.w2coding.library.exception.code.GlobalExceptionCode;

import io.grpc.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreExceptionCode implements GlobalExceptionCode {
	STORE_NOT_FOUND("S_S001", "Store not found", Status.NOT_FOUND),
	;

	private final String code;
	private final String message;
	private final Status status;

}
