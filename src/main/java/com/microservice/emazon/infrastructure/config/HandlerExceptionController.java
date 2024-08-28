package com.microservice.emazon.infrastructure.config;

import com.microservice.emazon.application.dto.ExceptionResponseDto;
import com.microservice.emazon.infrastructure.exeptions.ArticleException;
import com.microservice.emazon.infrastructure.exeptions.BrandException;
import com.microservice.emazon.infrastructure.exeptions.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingCategoryException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Category Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingBrandException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Brand Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingArticleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Article Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
