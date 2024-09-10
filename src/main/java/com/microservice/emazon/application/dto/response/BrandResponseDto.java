package com.microservice.emazon.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandResponseDto {

    private Long id;
    private String nameBrand;
    private String description;

}
