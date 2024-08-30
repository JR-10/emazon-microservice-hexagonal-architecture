package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Clase que es una Entidad para el mapeo de la tabla en la BD
 *
 * */

@Entity
@Table(name = "category_tbl" ) // definir el nombre que va a tener nuestra tabla
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<ArticleEntity> articles;

}
