package com.w2coding.securityserver.member.service;

import com.w2coding.securityserver.common.domain.enums.EntityStatus;
import com.w2coding.securityserver.member.domain.Member;
import com.w2coding.securityserver.member.domain.Store;
import com.w2coding.securityserver.member.domain.enums.MemberType;
import com.w2coding.securityserver.member.dto.SignInDto;
import com.w2coding.securityserver.member.dto.SignUpDto;
import com.w2coding.securityserver.member.exeption.DisabledStoreException;
import com.w2coding.securityserver.member.exeption.DuplicatedEmailException;
import com.w2coding.securityserver.member.exeption.SignInFailException;
import com.w2coding.securityserver.member.exeption.code.MemberExceptionCode;
import com.w2coding.securityserver.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
					.entityStatus(EntityStatus.ENABLE)
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
				.entityStatus(EntityStatus.DISABLE)
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

	@Nested
	@DisplayName("로그인")
	class SignIn {
		private String email = "test@gamil.com";
		private String password = "1234";
		private SignInDto signInDto = SignInDto.builder()
				.email(email)
				.password(password)
				.build();

		@Test
		@DisplayName("로그인 성공")
		public void success() {
			// given
			Member member = Member.builder()
					.entityStatus(EntityStatus.ENABLE)
					.build();

			given(memberRepository.findByEmail(eq(email)))
					.willReturn(Optional.of(member));

			// when
			memberService.signIn(signInDto);

			// then
			then(memberRepository).should().findByEmail(eq(email));
		}

		@Test
		@DisplayName("해당 이메일의 회원이 없는 경우")
		public void userEmailNotFound() {
			// given
			given(memberRepository.findByEmail(eq(email)))
					.willReturn(Optional.empty());

			// when, then
			then(memberRepository).should().findByEmail(eq(email));
			assertThatThrownBy(() -> memberService.signIn(signInDto))
					.isInstanceOf(SignInFailException.class)
					.hasFieldOrPropertyWithValue("code", MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD.getCode())
					.hasFieldOrPropertyWithValue("message", MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD.getMessage());
		}

		@Test
		@DisplayName("비밀번호가 다른 경우")
		public void passwordIsNotCorrect() {
			// given
			Member member = Member.builder()
					.password("5678")
					.entityStatus(EntityStatus.ENABLE)
					.build();

			given(memberRepository.findByEmail(eq(email)))
					.willReturn(Optional.of(member));

			// when, then
			then(memberRepository).should().findByEmail(eq(email));
			assertThatThrownBy(() -> memberService.signIn(signInDto))
					.isInstanceOf(SignInFailException.class)
					.hasFieldOrPropertyWithValue("code", MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD.getCode())
					.hasFieldOrPropertyWithValue("message", MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD.getMessage());
		}

		@Test
		@DisplayName("회원 상태가 disable 인 경우")
		public void userIsDisable() {
			// given
			Member member = Member.builder()
					.entityStatus(EntityStatus.DISABLE)
					.build();

			given(memberRepository.findByEmail(eq(email)))
					.willReturn(Optional.of(member));

			// when, then
			then(memberRepository).should().findByEmail(eq(email));
			assertThatThrownBy(() -> memberService.signIn(signInDto))
					.isInstanceOf(SignInFailException.class)
					.hasFieldOrPropertyWithValue("code", MemberExceptionCode.DISABLED_MEMBER.getCode())
					.hasFieldOrPropertyWithValue("message", MemberExceptionCode.DISABLED_MEMBER.getMessage());
		}

	}

}