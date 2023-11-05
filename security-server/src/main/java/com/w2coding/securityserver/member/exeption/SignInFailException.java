package com.w2coding.securityserver.member.exeption;

import com.w2coding.library.exception.GlobalException;
import com.w2coding.library.exception.code.GlobalExceptionCode;

public class SignInFailException extends GlobalException {

    public SignInFailException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
