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
		switch (response.getResult()) {
			case "S_ID001" ->
					throw new GrpcException(response.getResult(), response.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
