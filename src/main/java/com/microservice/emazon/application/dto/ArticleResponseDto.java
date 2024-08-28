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
        private Long idCategory;
        private Long idBrand;
        private Long quantity;
        private Long price;

}
