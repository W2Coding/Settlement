package com.w2coding.settlementserver.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2coding.settlementserver.member.domain.Member;
import com.w2coding.settlementserver.member.domain.Store;
import com.w2coding.settlementserver.member.domain.enums.MemberType;
import com.w2coding.settlementserver.member.domain.enums.Status;
import com.w2coding.settlementserver.member.dto.SignUpDto;
import com.w2coding.settlementserver.member.exeption.DisabledStoreException;
import com.w2coding.settlementserver.member.exeption.DuplicatedEmailException;
import com.w2coding.settlementserver.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		if (store.getStatus() == Status.DISABLE) {
			throw new DisabledStoreException();
		}
		return store;
	}

}
