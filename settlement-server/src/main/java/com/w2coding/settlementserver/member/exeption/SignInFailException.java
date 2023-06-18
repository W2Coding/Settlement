package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.common.exception.code.GlobalExceptionCode;

public class SignInFailException extends GlobalException {

    public SignInFailException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
