package com.w2coding.apigateway.common.exception;

import com.w2coding.apigateway.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GrpcException.class)
    public ResponseEntity<?> handleGrpcException(GrpcException e) {
        log.error("Occurred gRPC Exception: {} {}", e.getCode(), e.getMessage());
        return Response.error(e.getHttpStatus(), e.getCode(), e.getMessage());
    }

}
