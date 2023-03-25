package com.w2coding.apigateway.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Response<T> {

	private final String code;

	private final String message;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final T data;

	public static ResponseEntity<Response<?>> error(HttpStatus httpStatus, String code, String message) {
		return new ResponseEntity<>(new Response<>(code, message, null), httpStatus);
	}

	public static ResponseEntity<Response<?>> success(HttpStatus httpStatus) {
		return success(httpStatus, null);
	}

	public static <T> ResponseEntity<Response<?>> success(HttpStatus httpStatus, T data) {
		return new ResponseEntity<>(new Response<>("A_OK001", "성공적으로 처리하였습니다", data), httpStatus);
	}
}
