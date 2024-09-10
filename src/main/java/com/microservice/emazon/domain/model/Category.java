package com.microservice.emazon.domain.model;

/*
* Clase Categoria con atributos
* */
public class Category {

    private Long id;
    private String nameCategory;
    private String description;

    public Category(Long id, String nameCategory, String description) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
