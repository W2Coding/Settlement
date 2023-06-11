package com.w2coding.apigateway.member.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class SignInDtoTest {

	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();

	@Test
	@DisplayName("검증 성공")
	public void success() {
		// given
		SignInDto signInDto = SignInDto.builder()
			.email("user1@test.com")
			.password("1234")
			.build();

		// when
		Set<ConstraintViolation<SignInDto>> violations = validator.validate(signInDto);

		// then
		assertThat(violations).hasSize(0);
	}


	@Test
	@DisplayName("검증 실패")
	public void fail() {
		// given
		SignInDto signInDto = SignInDto.builder()
			.email("test")
			.password("")
			.build();

		// when
		Set<ConstraintViolation<SignInDto>> violations = validator.validate(signInDto);

		// then
		assertThat(violations).hasSize(2);

		assertThat(violations).extracting(ConstraintViolation::getPropertyPath)
			.extracting(Path::toString)
			.containsExactlyInAnyOrder("email", "password");

		assertThat(violations).extracting(ConstraintViolation::getMessage)
			.containsExactlyInAnyOrder("올바른 형식의 이메일 주소여야 합니다", "비어 있을 수 없습니다");
	}
}