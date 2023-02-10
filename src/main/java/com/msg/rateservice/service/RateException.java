package com.msg.rateservice.service;

import com.msg.rateservice.exception.ApplicationException;
import com.msg.rateservice.exception.ApplicationExceptionCode;

public class RateException extends ApplicationException {

    public RateException(ApplicationExceptionCode code, String pattern, Object... args) {
        super(code, pattern, args);
    }

    public enum RateExceptionCode implements ApplicationExceptionCode {
        RATE_FETCH_EXCEPTION
    }

}
