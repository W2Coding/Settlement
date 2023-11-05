package com.w2coding.securityserver.member.exeption;

import com.w2coding.library.exception.GlobalException;
import com.w2coding.securityserver.member.exeption.code.StoreExceptionCode;

public class StoreNotFoundException extends GlobalException {

	public StoreNotFoundException() {
		super(StoreExceptionCode.STORE_NOT_FOUND);
	}

}
