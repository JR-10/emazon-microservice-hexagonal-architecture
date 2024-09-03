package com.microservice.emazon.domain.exeptions;

public class BrandExceptions {


    private BrandExceptions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    public static class BrandNameAlreadyExistsException extends RuntimeException {
        public BrandNameAlreadyExistsException(String message) {
            super(message);
        }
    }

}
