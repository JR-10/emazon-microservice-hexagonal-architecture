package com.microservice.emazon.domain.exeptions;

public class ArticleException extends RuntimeException{
    public ArticleException(String message) {
        super(message);
    }
}
