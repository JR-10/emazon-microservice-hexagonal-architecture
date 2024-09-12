package com.microservice.emazon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


import java.util.List;
import java.util.Set;

import static com.microservice.emazon.application.util.ApplicationConstants.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleRequestDto {

    @NotNull(message = NAME_NOT_NULL)
    @NotBlank(message = NAME_ARTICLE_REQUIRED_MESSAGE)
    @Size(
            min = NAME_MIN_LENGTH,
            max = NAME_MAX_LENGTH,
            message = NAME_ARTICLE_LENGTH_MESSAGE)
    private String nameArticle;

    @NotNull(message = DESCRIPTION_NOT_NULL)
    @NotBlank(message = DESCRIPTION_ARTICLE_REQUIRED_MESSAGE)
    @Size(
            min = DESCRIPTION_MIN_LENGTH,
            max = DESCRIPTION_ARTICLE_MAX_LENGTH,
            message = DESCRIPTION_ARTICLE_LENGTH_MESSAGE)
    private String description;

    @NotNull(message = QUANTITY_ARTICLE_REQUIRED_MESSAGE)
    private Long quantity;

    @NotNull(message = PRICE_ARTICLE_REQUIRED_MESSAGE)
    private Long price;

    @NotNull(message = BRAND_ARTICLE_REQUIRED_MESSAGE)
    private Long brandId;

    @NotNull(message = CATEGORY_ARTICLE_REQUIRED_MESSAGE)
    @Size(
            min = CATEGORY_ID_MIN_LENGTH,
            max = CATEGORY_ID_MAX_LENGTH,
            message = CATEGORY_ID_LENGTH_MESSAGE)
    @UniqueElements(message = CATEGORY_ID_UNIQUE_MESSAGE) // este es un constraint personalizado, que valida que no haya elementos repetidos en la lista
    private List<Long> categoryIds;

}