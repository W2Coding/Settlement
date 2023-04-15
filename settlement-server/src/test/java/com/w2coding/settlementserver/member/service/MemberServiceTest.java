package com.w2coding.settlementserver.member.service;

import com.w2coding.settlementserver.member.domain.Store;
import com.w2coding.settlementserver.member.domain.enums.MemberType;
import com.w2coding.settlementserver.member.domain.enums.Status;
import com.w2coding.settlementserver.member.dto.SignUpDto;
import com.w2coding.settlementserver.member.exeption.DisabledStoreException;
import com.w2coding.settlementserver.member.exeption.DuplicatedEmailException;
import com.w2coding.settlementserver.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private StoreService storeService;

	@InjectMocks
	private MemberService memberService;

	@Nested
	@DisplayName("회원가입")
	class SignUp {
		private SignUpDto signUpDto;

		@BeforeEach
		void setUp() {
			signUpDto = SignUpDto.builder()
					.name("test")
					.email("test@gmail.com")
					.password("1234")
					.build();
		}

		@Test
		@DisplayName("관리자 회원가입 성공")
		void success() {
			// given
			signUpDto.setType(MemberType.ADMIN);

			given(memberRepository.existsByEmail(eq(signUpDto.getEmail())))
					.willReturn(false);

			// when
			memberService.signUp(signUpDto);

			// then
			then(memberRepository).should().existsByEmail(eq(signUpDto.getEmail()));
		}

		@Test
		@DisplayName("Worker 회원가입 성공")
		void successWorkerSignUp() {
			// given
			signUpDto.setType(MemberType.USER);
			signUpDto.setStoreId(1234567L);

			Store store = Store.builder()
					.id(1234567L)
					.name("testStore")
					.status(Status.ENABLE)
					.build();

			given(memberRepository.existsByEmail(eq(signUpDto.getEmail())))
				.willReturn(false);
			given(storeService.findById(eq(signUpDto.getStoreId())))
					.willReturn(store);

			// when
			memberService.signUp(signUpDto);

			// then
			then(memberRepository).should().existsByEmail(eq(signUpDto.getEmail()));
			then(storeService).should().findById(eq(signUpDto.getStoreId()));
		}
		
		@Test
		@DisplayName("이미 있는 이메일로 가입할 경우")
		void duplicatedEmail() {
			// given
			signUpDto.setType(MemberType.ADMIN);

			given(memberRepository.existsByEmail(eq(signUpDto.getEmail())))
					.willReturn(true);

			// when, then
			assertThatThrownBy(() -> memberService.signUp(signUpDto))
					.isInstanceOf(DuplicatedEmailException.class);
		}

		@Test
		@DisplayName("가게가 영업 가능 상태가 아닌 경우")
		void storeDisable() {
			// given
			signUpDto.setType(MemberType.USER);
			signUpDto.setStoreId(1234567L);

			Store store = Store.builder()
				.id(1234567L)
				.name("testStore")
				.status(Status.DISABLE)
				.build();

			given(memberRepository.existsByEmail(eq(signUpDto.getEmail())))
				.willReturn(false);
			given(storeService.findById(eq(signUpDto.getStoreId())))
				.willReturn(store);

			// when, then
			assertThatThrownBy(() -> memberService.signUp(signUpDto))
				.isInstanceOf(DisabledStoreException.class);
		}
	}

}