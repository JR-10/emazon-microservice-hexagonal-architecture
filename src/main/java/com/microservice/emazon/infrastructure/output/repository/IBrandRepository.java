package com.microservice.emazon.infrastructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;


public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByNameIgnoreCase(String name);
}