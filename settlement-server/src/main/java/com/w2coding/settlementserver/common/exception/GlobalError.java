package com.w2coding.settlementserver.common.exception;

import com.w2coding.settlementserver.common.exception.code.GlobalErrorCode;

public class GlobalError extends GlobalException{
    public GlobalError() {
        super(GlobalErrorCode.UNKNOWN_EXCEPTION);
    }
}
