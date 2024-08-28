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
    @NotBlank(message = "Category is required")
    private Long quantity;

    @NotNull
    @NotBlank(message = "Brand is required")
    private Long price;

}
