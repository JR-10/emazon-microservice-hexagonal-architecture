package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.ArticleRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    @PostMapping("/addArticle")
    public ResponseEntity<String> createArticle(@RequestBody @Valid ArticleRequestDto articleRequestDto ) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Ha creado exitosamente el articulo " + articleRequestDto.getName());
    }
}
