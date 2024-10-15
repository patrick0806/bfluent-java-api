package com.bfluent.management_api.Bfluent.domain.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
