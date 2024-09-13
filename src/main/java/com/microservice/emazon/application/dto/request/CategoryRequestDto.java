package com.microservice.emazon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.microservice.emazon.application.util.ApplicationConstants.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {

    @NotNull(message = NAME_NOT_NULL)
    @NotBlank(message = NAME_CATEGORY_REQUIRED_MESSAGE)
    @Size(
            min = NAME_MIN_LENGTH,
            max = NAME_MAX_LENGTH,
            message = NAME_CATEGORY_LENGTH_MESSAGE)
    private String nameCategory;

    @NotNull(message = DESCRIPTION_NOT_NULL)
    @NotBlank(message = DESCRIPTION_CATEGORY_REQUIRED_MESSAGE)
    @Size(
            min = DESCRIPTION_MIN_LENGTH,
            max = DESCRIPTION_CATEGORY_MAX_LENGTH,
            message = DESCRIPTION_CATEGORY_LENGTH_MESSAGE)
    private String description;

}
