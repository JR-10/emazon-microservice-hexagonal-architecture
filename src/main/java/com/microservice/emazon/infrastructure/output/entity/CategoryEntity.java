package com.microservice.emazon.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    private String name;

    private String description;

}
