package com.microservice.emazon.infrastructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String brandName);

}