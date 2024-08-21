package com.microservice.emazon.application.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {

    private String name;
    private String description;

}
