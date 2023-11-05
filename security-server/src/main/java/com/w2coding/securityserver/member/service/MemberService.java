package com.w2coding.securityserver.member.service;

import com.w2coding.securityserver.common.domain.enums.EntityStatus;
import com.w2coding.securityserver.member.domain.Member;
import com.w2coding.securityserver.member.domain.Store;
import com.w2coding.securityserver.member.domain.enums.MemberType;
import com.w2coding.securityserver.member.dto.SignUpDto;
import com.w2coding.securityserver.member.dto.SignInDto;
import com.w2coding.securityserver.member.repository.MemberRepository;
import com.w2coding.securityserver.member.exeption.DisabledStoreException;
import com.w2coding.securityserver.member.exeption.DuplicatedEmailException;
import com.w2coding.securityserver.member.exeption.SignInFailException;
import com.w2coding.securityserver.member.exeption.code.MemberExceptionCode;
import com.w2coding.securityserver.member.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;

	private final StoreService storeService;

	@Transactional
	public void signUp(SignUpDto signUpDto) {
		verifyEmailDuplicated(signUpDto.getEmail());
		Member member;
		if (isWorker(signUpDto.getType())) {
			Store store = getStoreIfEnable(signUpDto.getStoreId());
			member = signUpDto.toWorker(store);
		}
		else {
			member = signUpDto.toMember();
		}
		memberRepository.save(member);
	}

	private void verifyEmailDuplicated(String email) throws DuplicatedEmailException {
		if (memberRepository.existsByEmail(email)) {
			throw new DuplicatedEmailException();
		}
	}

	private boolean isWorker(MemberType memberType) {
		return memberType == MemberType.USER || memberType == MemberType.MANAGER;
	}

	private Store getStoreIfEnable(Long id) {
		Store store = storeService.findById(id);
		if (store.getEntityStatus() == EntityStatus.DISABLE) {
			throw new DisabledStoreException();
		}
		return store;
	}

	public void signIn(SignInDto signInDto) {
		Member member = memberRepository.findByEmail(signInDto.getEmail())
			.orElseThrow(() -> new SignInFailException(MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD));

		if (isNotEquals(member.getPassword(), signInDto.getPassword())) {
			throw new SignInFailException(MemberExceptionCode.WRONG_EMAIL_OR_PASSWORD);
		}

		if (member.isDisabled()) {
			throw new SignInFailException(MemberExceptionCode.DISABLED_MEMBER);
		}
	}

	private boolean isNotEquals(String originalPassword, String inputPassword) {
		return originalPassword.equals(inputPassword);
	}
}
