package com.microservice.emazon.application.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;

}
