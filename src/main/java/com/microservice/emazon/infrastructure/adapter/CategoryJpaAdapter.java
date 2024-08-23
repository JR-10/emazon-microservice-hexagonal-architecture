package com.microservice.emazon.infrastructure.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.infrastructure.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.exeptions.CategoryException;
import com.microservice.emazon.infrastructure.mapper.ICategoryEntityMapper;
import com.microservice.emazon.infrastructure.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter  implements ICategoryPersistencePort {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) {
            throw new CategoryException("No hay categorias");
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Page<Category> getCategories(String order, Pageable pageable) {
        if (order.equalsIgnoreCase("asc")){
            return mapCategoryEntityToCategory(categoryRepository.findAll(sortPageAscending(pageable)));
        }
        if (order.equalsIgnoreCase("desc")){
            return mapCategoryEntityToCategory(categoryRepository.findAll(sortPageDescending(pageable)));
        }
        throw new CategoryException("metodo de ordenamiento invalido ingrese 'asc' o 'desc'");
    }

    private Pageable sortPageDescending(Pageable pageable) {
        Sort sort = Sort.by("name").descending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Pageable sortPageAscending(Pageable pageable) {
        Sort sort = Sort.by("name").ascending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Page<Category>mapCategoryEntityToCategory(Page<CategoryEntity> page){
        return page.map(categoryEntityMapper::toCategory);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new CategoryException("Categoría existente");
        }
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {
        // not necessary yet
    }

    // TODO: modificacion 7 - Se agrega el metodo getPagination a la clase CategoryJpaAdapter implementado de la interfaz ICategoryPersistencePort
    @Override
    public List<Category> getPagination(Pageable pageable) {
        System.out.println("CategoryJpaAdapter.getPagination Page:" + pageable.getPageNumber() + " Size:" + pageable.getPageSize());
        return categoryEntityMapper.toCategoryList(categoryRepository.findAll(pageable).getContent());
    }

}
