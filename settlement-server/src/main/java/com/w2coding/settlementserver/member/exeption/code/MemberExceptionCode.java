package com.w2coding.settlementserver.member.exeption.code;

import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;

import io.grpc.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements GlobalExceptionCode {

	DUPLICATED_EMAIL("S_M001", "Duplicated email", Status.ALREADY_EXISTS),
	DISABLED_STORE("S_M002", "Disabled store", Status.FAILED_PRECONDITION),
	;

	private final String code;
	private final String message;
	private final Status status;

}
