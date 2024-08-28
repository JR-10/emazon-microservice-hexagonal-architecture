package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    /*
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleEntity> articles = new ArrayList<>();
     */
}
