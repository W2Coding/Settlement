package com.w2coding.settlementserver.member.dto;

import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.settlementserver.member.domain.enums.MemberType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {

	private final String name;
	private final String email;
	private final String password;
	private final MemberType type;
	private final Long storeId;


	public static SignUpDto of(SignUpRequest signUpRequest) {
		return SignUpDto.builder()
			.name(signUpRequest.getName())
			.email(signUpRequest.getEmail())
			.password(signUpRequest.getPassword())
			.type(MemberType.of(Character.forDigit(signUpRequest.getType(), 10)))
			.storeId(signUpRequest.getStoreId())
			.build();
	}

}
