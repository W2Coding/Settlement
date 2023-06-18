package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.member.exeption.code.MemberExceptionCode;

public class MemberNotFoundException extends GlobalException {

    public MemberNotFoundException() {
        super(MemberExceptionCode.MEMBER_NOT_FOUND);
    }
}
