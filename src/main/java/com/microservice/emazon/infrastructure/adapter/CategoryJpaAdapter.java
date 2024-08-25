package com.microservice.emazon.infrastructure.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.exeptions.CategoryException;
import com.microservice.emazon.infrastructure.mapper.ICategoryEntityMapper;
import com.microservice.emazon.infrastructure.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            throw new CategoryException("No hay categorias creadas");
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
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

    // TODO: modificacion 7 - Se agrega el metodo getPagination a la clase CategoryJpaAdapter implementado de la interfaz ICategoryPersistencePort
    @Override
    public Pagination<Category> getPagination(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending()? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryPage.getContent());

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categories
        );
    }
}
