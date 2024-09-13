package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "category_tbl" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;

    @Column(name = "description")
    private String description;


    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;

}
