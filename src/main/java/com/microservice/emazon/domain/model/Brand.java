package com.microservice.emazon.domain.model;

import java.util.List;

public class Brand {

    private Long id;
    private String nameBrand;
    private String description;

    public Brand() {
    }

    public Brand(Long id, String nameBrand, String description) {
        this.id = id;
        this.nameBrand = nameBrand;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand; ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
