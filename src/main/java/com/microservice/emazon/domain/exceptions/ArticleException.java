package com.microservice.emazon.domain.exceptions;

public class ArticleException {


    private  ArticleException() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static class ArticleNameAlreadyExistsException extends RuntimeException {
        public ArticleNameAlreadyExistsException(String message) {
            super(message);
        }
    }
}
