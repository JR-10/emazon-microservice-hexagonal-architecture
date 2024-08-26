// TODO: 2 - Create BrandRequestDto class
package com.microservice.emazon.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandRequestDto {

    @NotNull
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 120)
    private String description;
}
