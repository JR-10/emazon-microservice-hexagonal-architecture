package com.microservice.emazon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.microservice.emazon.application.util.ApplicationConstants.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandRequestDto {

    @NotNull(message = NAME_NOT_NULL)
    @NotBlank(message = NAME_BRAND_REQUIRED_MESSAGE)
    @Size(
            min = NAME_MIN_LENGTH,
            max = NAME_MAX_LENGTH,
            message = NAME_BRAND_LENGTH_MESSAGE)
    private String nameBrand;

    @NotNull(message = DESCRIPTION_NOT_NULL)
    @NotBlank(message = DESCRIPTION_BRAND_REQUIRED_MESSAGE)
    @Size(
            min = DESCRIPTION_MIN_LENGTH,
            max = DESCRIPTION_BRAND_MAX_LENGTH,
            message = DESCRIPTION_BRAND_LENGTH_MESSAGE)
    private String description;
    
}
