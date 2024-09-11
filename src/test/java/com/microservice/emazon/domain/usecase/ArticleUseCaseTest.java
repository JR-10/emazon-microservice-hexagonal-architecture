package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.exceptions.ArticleException;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;


    @InjectMocks
    private ArticleUseCase articleUseCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void saveArticle_SavesArticleSuccessfully() {
        Article article = new Article( 1L, "nameArticle", "description Article", 1L, 10.0, 1L,    List.of(1L));
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        List<String> categoryNames = List.of("CategoryName");

        when(articlePersistencePort.articleExistsByNameArticle(article.getNameArticle())).thenReturn(false);
        when(brandPersistencePort.getBrandById(article.getBrandId())).thenReturn(brand);
        when(categoryPersistencePort.getCategoryNamesByIds(article.getCategoryIds())).thenReturn(categoryNames);

        articleUseCase.saveArticle(article);

        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void saveArticle_ThrowsExceptionWhenArticleNameAlreadyExists() {
        Article article = new Article(1L, "nameArticle", "description Article", 1L, 10.0, 1L,    List.of(1L));

        when(articlePersistencePort.articleExistsByNameArticle(article.getNameArticle())).thenReturn(true);

        assertThrows(ArticleException.ArticleNameAlreadyExistsException.class, () -> articleUseCase.saveArticle(article));
    }


    @Test
    void getArticlesByParameters_ReturnsPaginatedArticles() {
        PaginationUtil paginationUtil = new PaginationUtil(1, 10, "nameArticle", true);
        Pagination<Article> expectedPagination = new Pagination<>(true, 1, 1, 10, List.of(new Article()));
        when(articlePersistencePort.getArticlesByParameters(paginationUtil)).thenReturn(expectedPagination);

        Pagination<Article> result = articleUseCase.getArticlesByParameters(paginationUtil);

        assertEquals(expectedPagination, result);
    }


    @Test
    void getArticlesByParameters_ReturnsEmptyPagination() {
        PaginationUtil paginationUtil = new PaginationUtil(1, 10, "nameArticle", true);
        Pagination<Article> expectedPagination = new Pagination<>(true, 1, 0, 0, List.of());
        when(articlePersistencePort.getArticlesByParameters(paginationUtil)).thenReturn(expectedPagination);

        Pagination<Article> result = articleUseCase.getArticlesByParameters(paginationUtil);

        assertEquals(expectedPagination, result);
    }


}