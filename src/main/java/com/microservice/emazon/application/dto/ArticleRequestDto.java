package com.microservice.emazon.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleRequestDto {

    @NotNull
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull
    private Long quantity;

    @NotNull
    private Long price;

}
