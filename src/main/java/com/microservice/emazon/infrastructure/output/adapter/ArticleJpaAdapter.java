package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.util.OrderingBy;
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
    public boolean articleExistsByNameArticle(String articleName) {
        return articleRepository.existsByNameArticle(articleName);
    }

    @Override
    public Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil) {

        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize()); // objeto de jpa que contiene la paginacion
        Page<ArticleEntity> ArticlePage = null; // de tipo Page que contiene la lista de articulos de entidad de ArticleEntity

        System.out.println("Valor de paginationUtil.getNameFilter() = " + paginationUtil.getNameFilter());

        return null;

        /*
        if (OrderingBy.BRAND_NAME.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por nombre de marca
            ArticlePage = paginationUtil.isAscending() ? articleRepository.findAllOrderingByBrandNameAsc(pageRequest) : articleRepository.findAllOrderingByBrandNameDesc(pageRequest);
        } else if (OrderingBy.NUMBER_OF_CATEGORIES.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por numero de categorias
            ArticlePage = paginationUtil.isAscending() ? articleRepository.findAllOrderingByNumberOfCategoriesAsc(pageRequest) : articleRepository.findAllOrderingByNumberOfCategoriesDesc(pageRequest);
        } else if (OrderingBy.ARTICLE_NAME.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por nombre de articulo
            ArticlePage = paginationUtil.isAscending() ? articleRepository.findAllOrderingByArticleNameAsc(pageRequest) : articleRepository.findAllOrderingByArticleNameDesc(pageRequest);
        }

        assert ArticlePage != null;
        List<Article> listArticles = articleEntityMapper.articleEntityListToArticleList(ArticlePage.getContent());
        System.out.println("Valor de listArticles = " + listArticles);

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                ArticlePage.getTotalPages(),
                ArticlePage.getTotalElements(),
                listArticles
        );
        */


        /*
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
        );*/
    }
}
