package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IArticleRepository;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;
    private final IBrandRepository brandRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public boolean articleExistsByName(String articleName) {
        return articleRepository.existsByName(articleName);
    }

    @Override
    public Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending()? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageArticleRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<ArticleEntity> articlesPage = articleRepository.findAll(pageArticleRequest);
        List<Article> articles = articleEntityMapper.articleEntityListToArticleList(articlesPage.getContent());
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                articlesPage.getTotalPages(),
                articlesPage.getTotalElements(),
                articles
        );
    }
}
