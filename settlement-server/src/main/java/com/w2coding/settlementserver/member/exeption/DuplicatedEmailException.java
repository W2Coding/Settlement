package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;

public class DuplicatedEmailException extends GlobalException {

    public DuplicatedEmailException() {
        super("S_M001", "Duplicated email");
    }
}
