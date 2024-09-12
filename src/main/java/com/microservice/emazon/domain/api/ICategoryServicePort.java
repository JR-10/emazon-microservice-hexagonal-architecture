package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

import java.util.List;


/*
* Interface para manejar las operaciones de las categorias en el dominiio
*/
public interface ICategoryServicePort {

    List<Category> getAllCategories();

    void saveCategory(Category category);

    Pagination<Category> getAllCategoriesPagination(PaginationUtil paginationUtil);

    List<Category> getAllByArticle(Long idArticle);

}
