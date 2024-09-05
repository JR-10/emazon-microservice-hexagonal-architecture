package com.microservice.emazon.application.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryByArticleResponseDto {

    private Long id;
    private String name;

}
