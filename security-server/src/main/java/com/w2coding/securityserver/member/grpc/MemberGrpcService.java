package com.w2coding.securityserver.member.grpc;

import com.w2coding.proto.common.GrpcResponse;
import com.w2coding.proto.member.MemberServiceGrpc;
import com.w2coding.proto.member.SignInRequest;
import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.securityserver.member.service.MemberService;
import com.w2coding.securityserver.member.dto.SignInDto;
import com.w2coding.securityserver.member.dto.SignUpDto;
import com.w2coding.securityserver.member.service.MemberService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberGrpcService extends MemberServiceGrpc.MemberServiceImplBase {

    private final MemberService memberService;

    @Override
    public void signUp(SignUpRequest request, StreamObserver<GrpcResponse> observer) {
        memberService.signUp(SignUpDto.of(request));
        observer.onNext(GrpcResponse.newBuilder().build());
        observer.onCompleted();
    }

    @Override
    public void signIn(SignInRequest request, StreamObserver<GrpcResponse> observer) {
        memberService.signIn(SignInDto.of(request));
        observer.onNext(GrpcResponse.newBuilder().build());
        observer.onCompleted();
    }

}
