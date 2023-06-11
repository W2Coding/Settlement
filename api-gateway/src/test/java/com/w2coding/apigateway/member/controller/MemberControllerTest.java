package com.w2coding.apigateway.member.controller;

import static org.assertj.core.api.BDDAssertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.w2coding.apigateway.common.response.Response;
import com.w2coding.apigateway.member.dto.SignInDto;
import com.w2coding.apigateway.member.dto.SignUpDto;
import com.w2coding.apigateway.member.service.MemberService;

import reactor.core.publisher.Flux;

@WebFluxTest(MemberController.class)
class MemberControllerTest {

	@MockBean
	private MemberService memberService;

	@Autowired
	private WebTestClient webTestClient;

	@Nested
	@DisplayName("회원가입 API")
	class signUp {

		@Test
		@DisplayName("회원가입 성공")
		void success() {
			// given
			SignUpDto signUpDto = SignUpDto.builder()
				.name("user1")
				.email("user1@test.com")
				.password("1234")
				.type('U')
				.build();
			willDoNothing().given(memberService).signUp(any(SignUpDto.class));

			// when
			WebTestClient.ResponseSpec exchange = webTestClient.post()
				.uri("/members/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(signUpDto)
				.exchange();

			// then
			then(memberService).should().signUp(any(SignUpDto.class));
			exchange.expectStatus().isCreated();

			Flux<Response> responseBody = exchange.returnResult(Response.class).getResponseBody();
			responseBody.subscribe(responseObj -> {
				assertThat(responseObj.getCode()).isEqualTo("A_OK001");
				assertThat(responseObj.getMessage()).isEqualTo("성공적으로 처리하였습니다");
			});
		}

	}

	@Nested
	@DisplayName("로그인 API")
	class SignIn {

		@Test
		@DisplayName("로그인 성공")
		void success() {
			// given
			SignInDto signInDto = SignInDto.builder()
				.email("user1@test.com")
				.password("1234")
				.build();
			willDoNothing().given(memberService).signIn(any(SignInDto.class));

			// when
			WebTestClient.ResponseSpec exchange = webTestClient.post()
				.uri("/members/sign-in")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(signInDto)
				.exchange();

			// then
			then(memberService).should().signIn(any(SignInDto.class));
			exchange.expectStatus().isOk();

			Flux<Response> responseBody = exchange.returnResult(Response.class).getResponseBody();
			responseBody.subscribe(responseObj -> {
				assertThat(responseObj.getCode()).isEqualTo("A_OK001");
				assertThat(responseObj.getMessage()).isEqualTo("성공적으로 처리하였습니다");
			});
		}

	}

}