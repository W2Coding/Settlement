package com.w2coding.apigateway.common.exception;

import com.w2coding.apigateway.common.response.Response;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<?> handleStatusRuntimeException(StatusRuntimeException e) {
        try {
            GrpcException exception = (GrpcException) e.getCause();
            log.error("Occurred gRPC Exception: {} {}", exception.getCode(), exception.getMessage());
            return Response.error(exception.getHttpStatus(e.getStatus()), exception.getCode(), exception.getMessage());
        }
        catch (Exception exception) {
            log.error("Occurred gRPC unknown Exception: {}", e.getMessage());
            return Response.internalServerError();
        }
    }

}
