package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.util.OrderingBy;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public boolean articleExistsByNameArticle(String articleName) {
        return articleRepository.existsByNameArticle(articleName);
    }

    @Override
    public Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil) {

        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize());
        Page<ArticleEntity> ArticlePage = null;


        if (OrderingBy.BRAND_NAME.getFieldName().equals(paginationUtil.getNameFilter())) {
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByBrandNameAsc(pageRequest)
                    : articleRepository.findAllOrderingByBrandNameDesc(pageRequest);
        } else if (OrderingBy.NUMBER_OF_CATEGORIES.getFieldName().equals(paginationUtil.getNameFilter())) {
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByNumberOfCategoriesAsc(pageRequest)
                    : articleRepository.findAllOrderingByNumberOfCategoriesDesc(pageRequest);
        } else if (OrderingBy.ARTICLE_NAME.getFieldName().equals(paginationUtil.getNameFilter())) {
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByArticleNameAsc(pageRequest)
                    : articleRepository.findAllOrderingByArticleNameDesc(pageRequest);
        }

        assert ArticlePage != null;
        List<Article> articles = articleEntityMapper.articleEntityListToArticleList(ArticlePage.getContent());

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                ArticlePage.getTotalPages(),
                ArticlePage.getTotalElements(),
                articles
        );

    }
}
