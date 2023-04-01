package com.w2coding.settlementserver.member.service;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2coding.settlementserver.member.domain.Member;
import com.w2coding.settlementserver.member.domain.enums.MemberType;
import com.w2coding.settlementserver.member.dto.SignUpDto;
import com.w2coding.settlementserver.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private WorkerService workerService;

	@InjectMocks
	private MemberService memberService;

	@Nested
	@DisplayName("회원가입")
	class SignUp {

		@Test
		@DisplayName("직원 회원가입 성공")
		void successWorkerSignUp() {
			// given
			SignUpDto signUpDto = SignUpDto.builder()
				.name("test")
				.email("test@gmail.com")
				.password("1234")
				.type(MemberType.USER)
				.storeId(1234567L)
				.build();

			given(memberRepository.existsByEmail(eq(signUpDto.getEmail())))
				.willReturn(false);
			willDoNothing()
				.given(workerService).registerWorker(any(Member.class));

			// when
			memberService.signUp(signUpDto);

			// then
			then(memberRepository).should().existsByEmail(eq(signUpDto.getEmail()));
			then(workerService).should().registerWorker(any(Member.class));
		}

		@Test
		@DisplayName("기타 회원가입 성공")
		void success() {
			// given


			// when

			// then
		}
	}

}