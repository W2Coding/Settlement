package com.w2coding.apigateway.member.dto;

import java.util.Set;

import static org.assertj.core.api.BDDAssertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.w2coding.proto.member.SignUpRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class SignUpDtoTest {

		private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		private final Validator validator = validatorFactory.getValidator();

		@Test
		@DisplayName("검증 성공")
		public void success() {
			// given
			SignUpDto signUpDto = SignUpDto.builder()
				.name("test")
				.email("user1@test.com")
				.password("1234")
				.type('U')
				.build();

			// when
			Set<ConstraintViolation<SignUpDto>> violations = validator.validate(signUpDto);

			// then
			assertThat(violations).hasSize(0);
		}

		@Test
		@DisplayName("검증 실패")
		public void testValidation() {
			// given
			SignUpDto signUpDto = SignUpDto.builder()
				.name("")
				.email("test")
				.password("")
				.type(null)
				.build();

			// when
			Set<ConstraintViolation<SignUpDto>> violations = validator.validate(signUpDto);

			// then
			assertThat(violations).hasSize(4);

			assertThat(violations).extracting(ConstraintViolation::getPropertyPath)
				.extracting(Path::toString)
				.containsExactlyInAnyOrder("name", "email", "password", "type");

			assertThat(violations).extracting(ConstraintViolation::getMessage)
				.containsExactlyInAnyOrder("비어 있을 수 없습니다", "올바른 형식의 이메일 주소여야 합니다", "비어 있을 수 없습니다", "널이어서는 안됩니다");

		}

		@Nested
		@DisplayName("프로토 객체 변경 테스트")
		class ToSignUpRequest {

			@Test
			@DisplayName("storeId 값이 null 이 아닐 때")
			void testStoreIdIsNotNull() {
				// given
				Long storeId = 1L;
				SignUpDto signUpDto = SignUpDto.builder()
					.name("test")
					.email("user1@test.com")
					.password("1234")
					.type('U')
					.storeId(storeId)
					.build();

				// when
				SignUpRequest signUpRequest = signUpDto.toSignUpRequest();

				// then
				assertThat(signUpRequest.getStoreId())
					.isEqualTo(storeId);
			}

			@Test
			@DisplayName("storeId 값이 null 일 때")
			void testStoreIdIsNull() {
				// given
				SignUpDto signUpDto = SignUpDto.builder()
					.name("user1")
					.email("user1@test.com")
					.password("1234")
					.type('U')
					.storeId(null)
					.build();

				// when
				SignUpRequest signUpRequest = signUpDto.toSignUpRequest();

				// then
				assertThat(signUpRequest.getStoreId())
					.isEqualTo(0);
			}
		}
	}