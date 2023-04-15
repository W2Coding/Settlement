package com.w2coding.settlementserver.member.grpc;

import org.springframework.stereotype.Service;

import com.w2coding.proto.common.GrpcResponse;
import com.w2coding.proto.member.MemberServiceGrpc;
import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;
import com.w2coding.settlementserver.member.dto.SignUpDto;
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
		try {
			memberService.signUp(SignUpDto.of(request));
			observer.onNext(GrpcResponse.newBuilder().build());
			observer.onCompleted();
		}
		catch (GlobalException exception) {
			GlobalExceptionCode exceptionCode = exception.getExceptionCode();
			observer.onNext(GrpcResponse.newBuilder()
					.setResult(exceptionCode.getCode())
					.setMessage(exceptionCode.getMessage())
				.build());
			observer.onCompleted();
		}
		catch (Exception exception) {
			log.error("Occurred unexpected error : {}", exception.getMessage());
			observer.onError(exception);
		}
	}

}
