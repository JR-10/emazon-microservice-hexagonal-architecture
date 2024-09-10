package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.dto.response.ArticleResponseDto;
import com.microservice.emazon.application.handler.IArticleHandler;
import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationConstants.SUCCESS_CREATED_ARTICLE_MESSAGE + articleRequestDto.getNameArticle()
        );
    }


    @GetMapping("/getArticlesByParameters")
    public ResponseEntity<Pagination<ArticleResponseDto>> getArticlesByParameters(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "nameArticle", required = false) String nameFilter,
            @RequestParam(defaultValue = "true", required = false) boolean ascending
    ){
        Pagination<ArticleResponseDto> listArticlePagination = articleHandler.getArticlesByParameters(
                new PaginationUtil(pageNumber, pageSize, nameFilter, ascending)
        );
        return ResponseEntity.ok().body(listArticlePagination);
    }
    
}
