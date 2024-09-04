package com.microservice.emazon.infrastructure.input.rest.exception;

import com.microservice.emazon.domain.exeptions.ArticleException;
import com.microservice.emazon.domain.exeptions.BrandExceptions;
import com.microservice.emazon.domain.exeptions.CategoryExceptions;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/*
* Clase que maneja las excepciones de la aplicacion
* */
@RestControllerAdvice
public class HandlerExceptionController {


    /*
    * Metodo que maneja las excepciones generales
    * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }


    /*
    * Metodo que maneja las excepciones de validacion de los campos de los objetos Dto`s
    * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /*
    * Metodo que maneja la excepcion de que la categoria no existe
    * */
    @ExceptionHandler(CategoryExceptions.CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryExceptions.CategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }


    /*
    * Metodo que maneja la excepcion de que el nombre de la categoria ya existe
    * */
    @ExceptionHandler(CategoryExceptions.CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNameAlreadyExistsException(CategoryExceptions.CategoryNameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }


    /*
    * Metodo que maneja la excepcion dew que el nombre de la marca ya existe
    * */
    @ExceptionHandler(BrandExceptions.BrandNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBrandNameAlreadyExistsException(BrandExceptions.BrandNameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }


    /*
    * Metodo que maneja que el nombre del articulo ya existe
    * */
    @ExceptionHandler(ArticleException.ArticleNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleArticleNameAlreadyExistsException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse( HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

}
