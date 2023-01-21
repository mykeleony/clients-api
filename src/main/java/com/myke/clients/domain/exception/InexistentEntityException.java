package com.myke.clients.domain.exception;

public class InexistentEntityException extends BusinessException {

    public static final long serialVersionUID = 1L;

    public InexistentEntityException(String message) {
        super(message);
    }

}
