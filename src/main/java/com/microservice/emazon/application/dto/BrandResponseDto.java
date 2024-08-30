// TODO: 3 - Create BrandResponseDto class
package com.microservice.emazon.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandResponseDto {

    private Long id;
    private String name;
    private String description;

}
