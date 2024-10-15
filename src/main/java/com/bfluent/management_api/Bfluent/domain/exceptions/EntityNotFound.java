package com.bfluent.management_api.Bfluent.domain.exceptions;

public class EntityNotFound extends RuntimeException {

    public  EntityNotFound(String message){
        super(message);
    }
}
