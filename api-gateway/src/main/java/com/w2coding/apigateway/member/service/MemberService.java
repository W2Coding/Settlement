package com.w2coding.apigateway.member.service;

import com.w2coding.apigateway.common.exception.GrpcException;
import com.w2coding.apigateway.member.dto.SignUpDto;
import com.w2coding.proto.common.GrpcResponse;
import com.w2coding.proto.member.MemberServiceGrpc;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub;

	public void signUp(SignUpDto signUpDto) {
		GrpcResponse response = memberServiceBlockingStub.signUp(signUpDto.toSignUpRequest());
		if (response.getResult().equals("")) {
			return;
		}

		HttpStatus httpStatus;
		switch (response.getResult()) {
			case "S_M001" -> httpStatus = HttpStatus.CONFLICT;
			case "S_M002" -> httpStatus = HttpStatus.BAD_REQUEST;
			case "S_S001" -> httpStatus = HttpStatus.NOT_FOUND;
			default -> httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		throw new GrpcException(response.getResult(), response.getMessage(), httpStatus);
	}

}
