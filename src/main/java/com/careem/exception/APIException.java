package com.careem.exception;

public class APIException extends CommerceException {

    private static final long serialVersionUID = 1L;

    public APIException(String message, Throwable e) {
        super(message, e);
    }

}
