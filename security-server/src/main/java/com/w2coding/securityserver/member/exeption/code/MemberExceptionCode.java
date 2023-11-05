package com.w2coding.securityserver.member.exeption.code;

import com.w2coding.library.exception.code.GlobalExceptionCode;

import io.grpc.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements GlobalExceptionCode {

	DUPLICATED_EMAIL("S_M001", "Duplicated email", Status.ALREADY_EXISTS),
	DISABLED_STORE("S_M002", "Disabled store", Status.FAILED_PRECONDITION),
	MEMBER_NOT_FOUND("S_M003", "Member not found", Status.NOT_FOUND),
	WRONG_EMAIL_OR_PASSWORD("S_M004", "Email or password is wrong", Status.UNAUTHENTICATED),
	DISABLED_MEMBER("S_M005", "Disabled member", Status.FAILED_PRECONDITION),
	;

	private final String code;
	private final String message;
	private final Status status;

}
