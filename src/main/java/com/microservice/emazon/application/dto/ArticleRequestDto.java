package com.microservice.emazon.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


import java.util.Set;

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

    @NotNull(message = "Quantity is required")
    private Long quantity;

    @NotNull(message = "Price is required")
    private Long price;

    @NotNull(message = "Brand ID is required")
    private Long brandId;

    @NotNull(message = "Category IDs are required")
    @Size(min = 1, max = 3, message = "Category IDs must be between 1 and 3")
    @UniqueElements(message = "Category IDs must be unique")
    private Set<Long> categoryIds;

}
