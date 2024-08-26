package com.microservice.emazon.infrastructure.exeptions;

public class BrandException  extends RuntimeException{

    public BrandException(String message) {
        super(message);
    }
}
