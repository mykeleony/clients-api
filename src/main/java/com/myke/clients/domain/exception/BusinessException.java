package com.myke.clients.domain.exception;

public class BusinessException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

}
