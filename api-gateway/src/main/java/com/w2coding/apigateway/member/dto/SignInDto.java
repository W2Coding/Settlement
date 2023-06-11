package com.w2coding.apigateway.member.dto;

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
}
