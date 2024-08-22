package com.microservice.emazon.infrastructure.exeptions;

public class CategoryException extends RuntimeException{

    public CategoryException(String message) {
        super(message);
    }
}
