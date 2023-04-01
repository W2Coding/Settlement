package com.w2coding.settlementserver.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2coding.settlementserver.member.dto.SignUpDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	@Transactional
	public void signUp(SignUpDto signUpDto) {


	}
}
