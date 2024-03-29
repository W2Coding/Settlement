package com.w2coding.apigateway.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w2coding.apigateway.common.response.Response;
import com.w2coding.apigateway.member.dto.SignUpDto;
import com.w2coding.apigateway.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
		memberService.signUp(signUpDto);
		return Response.success(HttpStatus.CREATED);
	}

}
