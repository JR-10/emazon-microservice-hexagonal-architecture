package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.domain.util.ValidationUtil;
import com.microservice.emazon.domain.exceptions.CategoryExceptions;


import java.util.List;

/*
* Clase que tiene la logica e implementa la interfaz de los puertos de servicio de categorias
* */
public class CategoryUseCase  implements ICategoryServicePort {

    // Inyeccion de dependencias
    private final ICategoryPersistencePort categoryPersistencePort;

    /*
    * Constructor que inyecta la dependencia de la persistencia de categorias
    * */
    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryEntityList = categoryPersistencePort.getAllCategories();
        if (categoryEntityList.isEmpty()) {
            throw new CategoryExceptions.CategoryNotFoundException(ApplicationConstants.NO_CATEGORIES_CREATED_MESSAGE);
        }
        return categoryPersistencePort.getAllCategories();
    }


    @Override
    public void saveCategory(Category category) {
        ValidationUtil.validateCategory(category);
        if (categoryPersistencePort.categoryExistsByName(category.getName())) {
            throw new CategoryExceptions.CategoryNameAlreadyExistsException(ApplicationConstants.CATEGORY_NAME_ALREADY_EXISTS_MESSAGE);
        }
        categoryPersistencePort.saveCategory(category);
    }


    @Override
    public Pagination<Category> getAllCategoriesPagination(PaginationUtil paginationUtil) {
        return categoryPersistencePort.getAllCategoriesPagination(paginationUtil);
    }

}
