package com.w2coding.securityserver.member.dto;

import com.w2coding.proto.member.SignUpRequest;
import com.w2coding.securityserver.common.domain.enums.EntityStatus;
import com.w2coding.securityserver.member.domain.Member;
import com.w2coding.securityserver.member.domain.Store;
import com.w2coding.securityserver.member.domain.Worker;
import com.w2coding.securityserver.member.domain.enums.MemberType;
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
			.type(MemberType.of((char) signUpRequest.getType()))
			.storeId(signUpRequest.getStoreId())
			.build();
	}

	public Worker toWorker(Store store) {
		Worker.WorkerBuilder builder = (Worker.WorkerBuilder) createMember(Worker.builder());
		return builder
			.store(store)
			.build();
	}

	public Member toMember() {
		return createMember(Member.builder()).build();
	}

	private Member.MemberBuilder createMember(Member.MemberBuilder builder) {
		return builder
			.name(name)
			.email(email)
			.password(password)
			.type(type)
			.entityStatus(EntityStatus.ENABLE);
	}
}
