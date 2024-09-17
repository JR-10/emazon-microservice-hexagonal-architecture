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
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter  implements ICategoryPersistencePort {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }


    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryEntityToCategory(category));
    }

    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.findByNameCategory(categoryName).isPresent();
    }


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


    @Override
    public List<String> getCategoryNamesByIds(List<Long> ids) {
        return categoryRepository.findAllById(ids).stream()
                .map(CategoryEntity::getNameCategory)
                .collect(Collectors.toList());
    }


    @Override
    public List<Category> getAllByArticle(Long idArticle) {
        List<CategoryEntity> categories = categoryRepository.findCategoriesByArticleId(idArticle);
        return categoryEntityMapper.toCategoryList(categories);
    }
}
