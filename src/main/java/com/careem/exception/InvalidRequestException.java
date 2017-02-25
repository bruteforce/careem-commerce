package com.careem.exception;

public class InvalidRequestException extends CommerceException {

    private static final long serialVersionUID = 1L;

    private final String param;

    public InvalidRequestException(String message, String param, Throwable e) {
        super(message, e);
        this.param = param;
    }

    public String getParam() {
        return param;
    }

}
