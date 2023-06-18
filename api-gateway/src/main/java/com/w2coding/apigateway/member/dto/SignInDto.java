package com.w2coding.apigateway.member.dto;

import com.w2coding.proto.member.SignInRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInDto {

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	public SignInRequest toSignInRequest() {
		return SignInRequest.newBuilder()
				.setEmail(this.email)
				.setPassword(this.password)
				.build();
	}
}
