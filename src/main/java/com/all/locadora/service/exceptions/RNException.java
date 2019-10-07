package com.all.locadora.service.exceptions;

public class RNException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RNException(String msg) {
        super(msg);
    }

    public RNException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
