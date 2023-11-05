package com.w2coding.securityserver.member.exeption;

import com.w2coding.library.exception.GlobalException;
import com.w2coding.securityserver.member.exeption.code.MemberExceptionCode;

public class DuplicatedEmailException extends GlobalException {

    public DuplicatedEmailException() {
        super(MemberExceptionCode.DUPLICATED_EMAIL);
    }

}
