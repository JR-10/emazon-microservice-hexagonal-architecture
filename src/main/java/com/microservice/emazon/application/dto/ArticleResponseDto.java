package com.microservice.emazon.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleResponseDto {

        private Long id;
        private String name;
        private String description;
        private Long brandId;
        private Long quantity;
        private Long price;
}
