package com.enjoytrip.group.exception;

import com.enjoytrip.common.exception.BaseException;
import com.enjoytrip.common.exception.ExceptionCode;

public class GroupException extends BaseException {

    public GroupException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public GroupException(String code, String title, String message) {
        super(code, title, message);
    }
}
