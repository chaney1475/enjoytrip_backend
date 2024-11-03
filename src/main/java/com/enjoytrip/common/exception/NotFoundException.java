package com.enjoytrip.common.exception;

import com.amazonaws.services.kms.model.ConnectionErrorCodeType;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

    public NotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public NotFoundException(String title, String message) {
        super("NOT_FOUND", title, message);
    }

}
