package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.handler.IArticleHandler;
import com.microservice.emazon.application.util.ApplicationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final IArticleHandler articleHandler;


    @Operation(summary = "Add a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Article already exists", content = @Content)
    })
    @PostMapping("/addArticle")
    public ResponseEntity<String> saveArticle(@RequestBody @Valid ArticleRequestDto articleRequestDto ) {
        articleHandler.saveArticle(articleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationConstants.SUCCESS_CREATED_ARTICLE_MESSAGE + articleRequestDto.getName());
    }
}
