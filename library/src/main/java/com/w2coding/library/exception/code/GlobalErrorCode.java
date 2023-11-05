package com.w2coding.library.exception.code;

import io.grpc.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements GlobalExceptionCode {
    UNKNOWN_EXCEPTION("S_UN001", "Contact to administrator", Status.INTERNAL)
    ;

    private final String code;
    private final String message;
    private final Status status;
}
