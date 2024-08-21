package com.microservice.emazon.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min = 10, max = 90)
    private String description;

}
