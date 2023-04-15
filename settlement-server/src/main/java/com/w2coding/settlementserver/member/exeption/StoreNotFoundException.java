package com.w2coding.settlementserver.member.exeption;

import com.w2coding.settlementserver.common.exception.GlobalException;
import com.w2coding.settlementserver.member.exeption.code.StoreExceptionCode;

public class StoreNotFoundException extends GlobalException {

	public StoreNotFoundException() {
		super(StoreExceptionCode.STORE_NOT_FOUND);
	}

}
