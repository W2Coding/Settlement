package com.w2coding.settlementserver.member.dto;

import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.settlementserver.member.domain.Member;
import com.w2coding.settlementserver.member.domain.Store;
import com.w2coding.settlementserver.member.domain.Worker;
import com.w2coding.settlementserver.member.domain.enums.MemberType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {

	private String name;
	private String email;
	private String password;
	private MemberType type;
	private Long storeId;

	public static SignUpDto of(SignUpRequest signUpRequest) {
		return SignUpDto.builder()
			.name(signUpRequest.getName())
			.email(signUpRequest.getEmail())
			.password(signUpRequest.getPassword())
			.type(MemberType.of(Character.forDigit(signUpRequest.getType(), 10)))
			.storeId(signUpRequest.getStoreId())
			.build();
	}

	private Worker toWorker(Store store) {
		return Worker.builder()
				.name(name)
				.email(email)
				.password(password)
				.type(type)
				.store(store)
				.build();
	}

	private Member toMember() {
		return Member.builder()
				.name(name)
				.email(email)
				.password(password)
				.type(type)
				.build();
	}
}
