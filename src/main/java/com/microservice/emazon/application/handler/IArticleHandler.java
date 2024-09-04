package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.dto.response.ArticleResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IArticleHandler {

    void saveArticle(ArticleRequestDto articleRequestDto);

    Pagination<ArticleResponseDto> getArticlesByParameters (PaginationUtil paginationUtil);
}
