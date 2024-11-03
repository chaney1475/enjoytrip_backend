package com.enjoytrip.attraction.exception;

import com.enjoytrip.common.exception.BaseException;
import com.enjoytrip.common.exception.ExceptionCode;

public class AttractionException extends BaseException {

    public AttractionException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public AttractionException(String code, String title, String message) {
        super(code, title, message);
    }
}
