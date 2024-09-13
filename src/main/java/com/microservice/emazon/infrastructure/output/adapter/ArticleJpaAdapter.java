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

        // validamos el filtro de ordenamiento para saber por que campo se va a ordenar
        if (OrderingBy.BRAND_NAME.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por nombre de marca
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByBrandNameAsc(pageRequest)
                    : articleRepository.findAllOrderingByBrandNameDesc(pageRequest);
        } else if (OrderingBy.NUMBER_OF_CATEGORIES.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por numero de categorias
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByNumberOfCategoriesAsc(pageRequest)
                    : articleRepository.findAllOrderingByNumberOfCategoriesDesc(pageRequest);
        } else if (OrderingBy.ARTICLE_NAME.getFieldName().equals(paginationUtil.getNameFilter())) { // si el filtro es por nombre de articulo
            ArticlePage = paginationUtil.isAscending()
                    ? articleRepository.findAllOrderingByArticleNameAsc(pageRequest)
                    : articleRepository.findAllOrderingByArticleNameDesc(pageRequest);
        }

        assert ArticlePage != null; // validamos que la pagina no sea nula
        List<Article> articles = articleEntityMapper.articleEntityListToArticleList(ArticlePage.getContent()); // mapeamos la lista de articulos de entidad a la lista de articulos de dominio

        // retornamos la paginacion
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                ArticlePage.getTotalPages(),
                ArticlePage.getTotalElements(),
                articles
        );

    }
}
