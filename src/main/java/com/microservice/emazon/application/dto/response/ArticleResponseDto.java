package com.microservice.emazon.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleResponseDto {

        private Long id;
        private String name;
        private String description;
        private Long quantity;
        private Long price;
        private BrandResponseDto brand;
        private List<CategoryResponseDto> categories;
}
