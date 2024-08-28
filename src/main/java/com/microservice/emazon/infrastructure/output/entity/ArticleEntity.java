package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "article_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long quantity;

    private Long price;

    /*
    @ManyToMany
    @JoinTable(
            name = "article_category_tbl",
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categories;


    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private BrandEntity brand;
    */
}
