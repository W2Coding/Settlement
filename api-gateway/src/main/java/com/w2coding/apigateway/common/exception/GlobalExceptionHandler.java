package com.w2coding.apigateway.common.exception;

import com.w2coding.apigateway.common.response.Response;
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
