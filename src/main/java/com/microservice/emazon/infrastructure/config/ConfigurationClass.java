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

/*
* Clase de configuración de Spring que define los beans de los puertos de los casos de uso
* */
@Configuration
public class ConfigurationClass {

    /*
    * Metodo que define el bean del puerto de persistencia de la categoria
    * */
    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistencePort categoryPersistentPort) {
        return new CategoryUseCase(categoryPersistentPort);
    }

    /*
    * Metodo que define el bean del puerto de persistencia de la marca
    * */
    @Bean
    public IBrandServicePort brandPersistencePort(IBrandPersistencePort brandPersistentPort) {
        return new BrandUseCase(brandPersistentPort);
    }

    /*
    * Metodo que define el bean del puerto de persistencia del articulo
    * */
    @Bean
    public IArticleServicePort articleServicePort(IArticlePersistencePort articlePersistencePort) {
        return new ArticleUseCase(articlePersistencePort);
    }

}
