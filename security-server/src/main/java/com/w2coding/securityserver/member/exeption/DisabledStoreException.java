package com.w2coding.securityserver.member.exeption;

import com.w2coding.library.exception.GlobalException;
import com.w2coding.securityserver.member.exeption.code.MemberExceptionCode;

public class DisabledStoreException extends GlobalException {

	public DisabledStoreException() {
		super(MemberExceptionCode.DISABLED_STORE);
	}

}
