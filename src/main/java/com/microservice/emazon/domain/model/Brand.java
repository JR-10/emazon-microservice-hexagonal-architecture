// TODO: 1 - Create a Brand class with nameBrand and descriptionBrand attributes
package com.microservice.emazon.domain.model;

import java.util.List;

public class Brand {

    private Long id;
    private String name;
    private String description;
    private List<Article> articles;

    public Brand(Long id, String name, String description, List<Article> articles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
