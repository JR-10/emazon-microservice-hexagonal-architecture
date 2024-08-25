package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.exeptions.CategoryException;

import java.util.List;
import java.util.Optional;

public class CategoryUseCase  implements ICategoryServicePort {


    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }


    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {
        if (!isValidCategory(category)){
            throw new CategoryException("Nombre o descripción supera el maximo de caracteres permitidos");
        }
        categoryPersistencePort.saveCategory(category);
    }

    private boolean isValidCategory(Category category){
        return category.getName().length() < 50 &&
                category.getDescription().length() < 90;
    }


    // TODO: modificacion 5 - Se agrega el metodo getPagination a la clase CategoryUseCase implementando la interfaz ICategoryServicePort
    @Override
    public Pagination<Category> getPagination(PaginationUtil paginationUtil) {
        return categoryPersistencePort.getPagination(paginationUtil);
    }

}
