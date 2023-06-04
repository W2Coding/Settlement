package com.w2coding.apigateway.member.dto;

import com.w2coding.proto.member.SignUpRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {

	@NotEmpty
	private String name;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	/**
	 * U : User,
	 * M : Manager,
	 * L : Manager Leader,
	 * A : Admin
	 * */
	@NotNull
	private Character type;
	
	private Long storeId;

	public SignUpRequest toSignUpRequest() {
		return SignUpRequest.newBuilder()
				.setName(name)
				.setEmail(email)
				.setPassword(password)
				.setType(type)
				.setStoreId(storeId == null ? 0 : storeId)
				.build();
	}

}
