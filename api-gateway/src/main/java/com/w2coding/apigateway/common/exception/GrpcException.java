package com.w2coding.apigateway.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class GrpcException extends RuntimeException {

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
