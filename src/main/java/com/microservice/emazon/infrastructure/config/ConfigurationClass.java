package com.microservice.emazon.infrastructure.config;

import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.usecase.CategoryUseCase;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

@Configuration
public class ConfigurationClass {

    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistencePort categoryPersistentPort) {
        return new CategoryUseCase(categoryPersistentPort);
    }

}
