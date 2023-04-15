package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.member.exeption.code.MemberExceptionCode;

public class DisabledStoreException extends GlobalException {

	public DisabledStoreException() {
		super(MemberExceptionCode.DISABLED_STORE);
	}

}
