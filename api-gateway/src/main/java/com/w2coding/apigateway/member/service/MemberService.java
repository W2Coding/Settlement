package com.w2coding.apigateway.member.service;

import com.w2coding.apigateway.member.dto.SignInDto;
import com.w2coding.apigateway.member.dto.SignUpDto;
import com.w2coding.proto.member.MemberServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub;

    public void signUp(SignUpDto signUpDto) {
        memberServiceBlockingStub.signUp(signUpDto.toSignUpRequest());
    }

    public void signIn(SignInDto signInDto) {
        memberServiceBlockingStub.signIn(signInDto.toSignInRequest());
    }

}
