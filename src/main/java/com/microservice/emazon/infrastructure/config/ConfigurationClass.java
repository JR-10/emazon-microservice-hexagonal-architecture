package com.microservice.emazon.infrastructure.config;

import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.usecase.ArticleUseCase;
import com.microservice.emazon.domain.usecase.BrandUseCase;
import com.microservice.emazon.domain.usecase.CategoryUseCase;
import org.springframework.context.annotation.Configuration;



import org.springframework.context.annotation.Bean;


@Configuration
public class ConfigurationClass {


    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistencePort categoryPersistentPort) {
        return new CategoryUseCase(categoryPersistentPort);
    }


    @Bean
    public IBrandServicePort brandPersistencePort(IBrandPersistencePort brandPersistentPort) {
        return new BrandUseCase(brandPersistentPort);
    }


    @Bean
    public IArticleServicePort articleServicePort(IArticlePersistencePort articlePersistencePort, IBrandPersistencePort brandPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        return new ArticleUseCase(articlePersistencePort, brandPersistencePort, categoryPersistencePort);
    }

}
