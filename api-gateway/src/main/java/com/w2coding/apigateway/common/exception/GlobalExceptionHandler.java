package com.w2coding.apigateway.common.exception;

import com.w2coding.apigateway.common.response.Response;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<?> handleStatusRuntimeException(StatusRuntimeException e) {
        String code = e.getTrailers().get(Metadata.Key.of("code", Metadata.ASCII_STRING_MARSHALLER));
        String message = e.getTrailers().get(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER));

        log.error("Occurred gRPC Exception: {} {}", code, message);
        return Response.error(getHttpStatus(e.getStatus()), code, message);
    }

    private HttpStatus getHttpStatus(Status status) {
        return switch (status.getCode()) {
            case OK -> HttpStatus.OK;
            case CANCELLED, DEADLINE_EXCEEDED -> HttpStatus.REQUEST_TIMEOUT;
            case INVALID_ARGUMENT -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case ALREADY_EXISTS, ABORTED -> HttpStatus.CONFLICT;
            case PERMISSION_DENIED -> HttpStatus.FORBIDDEN;
            case RESOURCE_EXHAUSTED -> HttpStatus.TOO_MANY_REQUESTS;
            case FAILED_PRECONDITION -> HttpStatus.PRECONDITION_FAILED;
            case OUT_OF_RANGE -> HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
            case UNIMPLEMENTED -> HttpStatus.NOT_IMPLEMENTED;
            case UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
            case UNKNOWN, INTERNAL, DATA_LOSS -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
