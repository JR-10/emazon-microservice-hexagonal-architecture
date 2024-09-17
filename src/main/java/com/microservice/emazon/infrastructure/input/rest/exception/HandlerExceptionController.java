package com.microservice.emazon.infrastructure.input.rest.exception;

import com.microservice.emazon.domain.exceptions.ArticleException;
import com.microservice.emazon.domain.exceptions.BrandExceptions;
import com.microservice.emazon.domain.exceptions.CategoryExceptions;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class HandlerExceptionController {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(CategoryExceptions.CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryExceptions.CategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }



    @ExceptionHandler(CategoryExceptions.CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNameAlreadyExistsException(CategoryExceptions.CategoryNameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }



    @ExceptionHandler(BrandExceptions.BrandNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBrandNameAlreadyExistsException(BrandExceptions.BrandNameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }


    @ExceptionHandler(BrandExceptions.BrandNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBrandNotFoundException(BrandExceptions.BrandNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }



    @ExceptionHandler(ArticleException.ArticleNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleArticleNameAlreadyExistsException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse( HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

}
