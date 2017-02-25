package com.careem.exception;

public abstract class CommerceException extends Exception {

    public CommerceException(String message) {
        super(message, null);
    }

    public CommerceException(String message, Throwable e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;

}
