package com.w2coding.securityserver.member.exeption;

import com.w2coding.securityserver.member.exeption.code.MemberExceptionCode;
import com.w2coding.library.exception.GlobalException;

public class MemberNotFoundException extends GlobalException {

    public MemberNotFoundException() {
        super(MemberExceptionCode.MEMBER_NOT_FOUND);
    }
}
