package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {

    List<Category> getAllCategories();

    Page<Category> getCategories(String order, Pageable pageable);

    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);

    void deleteCategory(Long id);

    // TODO: modificacion 6 - Se agrega el metodo getPagination a la interfaz ICategoryPersistencePort
    List<Category> getCategoriesByPagination(Pageable pageable);
}
