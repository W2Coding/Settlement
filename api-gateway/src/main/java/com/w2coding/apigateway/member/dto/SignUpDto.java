package com.w2coding.apigateway.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
	@NotEmpty
	private Character type;
	
	private Long storeId;

}
