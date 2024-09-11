package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.output.mapper.ICategoryEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class CategoryJpaAdapterTest {


    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ReturnsCategoryList() {
        List<CategoryEntity> categoryEntities = List.of(new CategoryEntity());
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        List<Category> result = categoryJpaAdapter.getAllCategories();

        assertEquals(categories, result);
    }


    @Test
    void saveCategory_SavesCategorySuccessfully() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryRepository.findByNameCategory(anyString())).thenReturn(Optional.empty());
        when(categoryEntityMapper.categoryEntityToCategory(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        verify(categoryRepository, times(1)).save(categoryEntity);
    }


    @Test
    void getCategory_ReturnsEmptyOptional() {
        Optional<Category> result = categoryJpaAdapter.getCategory(1L);
        assertTrue(result.isEmpty());
    }


    @Test
    void categoryExistsByName_ReturnsTrueWhenCategoryExists() {
        String categoryName = "ExistingCategory";
        when(categoryRepository.findByNameCategory(categoryName)).thenReturn(Optional.of(new CategoryEntity()));

        boolean result = categoryJpaAdapter.categoryExistsByName(categoryName);

        assertTrue(result);
    }

    @Test
    void categoryExistsByName_ReturnsFalseWhenCategoryDoesNotExist() {
        String categoryName = "NonExistingCategory";
        when(categoryRepository.findByNameCategory(categoryName)).thenReturn(Optional.empty());

        boolean result = categoryJpaAdapter.categoryExistsByName(categoryName);

        assertFalse(result);
    }

    @Test
    void getAllByArticle_ReturnsCategoryListWhenArticleHasCategories() {
        Long articleId = 1L;
        List<CategoryEntity> categoryEntities = List.of(new CategoryEntity());
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));

        when(categoryRepository.findCategoriesByArticleId(articleId)).thenReturn(categoryEntities);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        List<Category> result = categoryJpaAdapter.getAllByArticle(articleId);

        assertEquals(categories, result);
    }

    @Test
    void getAllByArticle_ReturnsEmptyListWhenArticleHasNoCategories() {
        Long articleId = 1L;
        List<CategoryEntity> emptyCategoryEntities = List.of();
        List<Category> emptyCategories = List.of();

        when(categoryRepository.findCategoriesByArticleId(articleId)).thenReturn(emptyCategoryEntities);
        when(categoryEntityMapper.toCategoryList(emptyCategoryEntities)).thenReturn(emptyCategories);

        List<Category> result = categoryJpaAdapter.getAllByArticle(articleId);

        assertEquals(emptyCategories, result);
    }


    @Test
    void getCategoryNamesByIds_ReturnsCategoryNamesWhenIdsAreValid() {
        List<Long> ids = List.of(1L, 2L);
        List<CategoryEntity> categoryEntities = List.of(
                new CategoryEntity(1L, "Category1", "Description1", List.of(new ArticleEntity[]{})),
                new CategoryEntity(2L, "Category2", "Description2", List.of(new ArticleEntity[]{}))
        );
        List<String> expectedNames = List.of("Category1", "Category2");

        when(categoryRepository.findAllById(ids)).thenReturn(categoryEntities);

        List<String> result = categoryJpaAdapter.getCategoryNamesByIds(ids);

        assertEquals(expectedNames, result);
    }

    @Test
    void getCategoryNamesByIds_ReturnsEmptyListWhenNoIdsProvided() {
        List<Long> ids = List.of();
        List<CategoryEntity> categoryEntities = List.of();
        List<String> expectedNames = List.of();

        when(categoryRepository.findAllById(ids)).thenReturn(categoryEntities);

        List<String> result = categoryJpaAdapter.getCategoryNamesByIds(ids);

        assertEquals(expectedNames, result);
    }

    @Test
    void getCategoryNamesByIds_ReturnsEmptyListWhenIdsNotFound() {
        List<Long> ids = List.of(1L, 2L);
        List<CategoryEntity> categoryEntities = List.of();
        List<String> expectedNames = List.of();

        when(categoryRepository.findAllById(ids)).thenReturn(categoryEntities);

        List<String> result = categoryJpaAdapter.getCategoryNamesByIds(ids);

        assertEquals(expectedNames, result);
    }


    @Test
    void getAllCategoriesPagination_ReturnsPaginatedCategories() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 10, "name", true);
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        List<CategoryEntity> categoryEntities = List.of(new CategoryEntity());
        Page<CategoryEntity> categoryPage = new PageImpl<>(categoryEntities);
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));

        when(categoryRepository.findAll(pageRequest)).thenReturn(categoryPage);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        Pagination<Category> result = categoryJpaAdapter.getAllCategoriesPagination(paginationUtil);

        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        assertEquals(categories, result.getData());
    }

}