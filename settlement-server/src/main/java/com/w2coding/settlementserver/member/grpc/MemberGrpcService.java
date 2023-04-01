package com.w2coding.settlementserver.member.grpc;

import org.springframework.stereotype.Service;

import com.w2coding.proto.common.GrpcResponse;
import com.w2coding.proto.member.MemberServiceGrpc;
import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.settlementserver.member.service.MemberService;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberGrpcService extends MemberServiceGrpc.MemberServiceImplBase {

	private final MemberService memberService;

	@Override
	public void signUp(SignUpRequest request, StreamObserver<GrpcResponse> observer) {

	}

}
