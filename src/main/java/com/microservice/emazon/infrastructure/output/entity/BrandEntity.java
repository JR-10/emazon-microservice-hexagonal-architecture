package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "name_brand")
    private String nameBrand;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;
}
