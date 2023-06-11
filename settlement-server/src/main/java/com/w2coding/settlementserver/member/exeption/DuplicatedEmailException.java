package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.member.exeption.code.MemberExceptionCode;

public class DuplicatedEmailException extends GlobalException {

    public DuplicatedEmailException() {
        super(MemberExceptionCode.DUPLICATED_EMAIL);
    }

}
