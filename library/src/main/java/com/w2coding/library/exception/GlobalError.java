package com.w2coding.library.exception;

import com.w2coding.library.exception.code.GlobalErrorCode;

public class GlobalError extends GlobalException{
    public GlobalError() {
        super(GlobalErrorCode.UNKNOWN_EXCEPTION);
    }
}
