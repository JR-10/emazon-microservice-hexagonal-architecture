package com.microservice.emazon.infrastructure.exeptions;

public class ArticleException extends RuntimeException{
    public ArticleException(String message) {
        super(message);
    }
}
