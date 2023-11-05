package com.w2coding.securityserver.member.dto;

import com.w2coding.proto.member.SignInRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInDto {

    private String email;

    private String password;

    public static SignInDto of(SignInRequest signInRequest) {
        return SignInDto.builder()
                .email(signInRequest.getEmail())
                .password(signInRequest.getPassword())
                .build();
    }

}
