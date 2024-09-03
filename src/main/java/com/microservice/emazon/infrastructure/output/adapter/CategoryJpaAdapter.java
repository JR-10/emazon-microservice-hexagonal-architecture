package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.repository.ICategoryRepository;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.output.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/*
* Clase que implementa el puerto de persistencia de categorias
* */
@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter  implements ICategoryPersistencePort {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    /*
    * Metodo que obtiene todas las categorias de la base de datos para mostrarlas
    * */
    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    /*
    * Metodo que guarda una categoria en la base de datos
    * */
    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryEntityToCategory(category));
    }

    /*
    * Metodo que verifica si existe una categoria por nombre en la base de datos
    * */
    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }


    /*
    * Metodo que obtiene todas las categorias con paginacion y ordenamiento por nombre de forma ascendente o descendente
    * */
    @Override
    public Pagination<Category> getAllCategoriesPagination(PaginationUtil paginationUtil) {
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
