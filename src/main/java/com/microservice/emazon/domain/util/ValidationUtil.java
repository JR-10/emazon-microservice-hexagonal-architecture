package com.microservice.emazon.domain.util;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.model.Category;

public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateCategory(Category category){

        if(category.getName() == null || category.getName().isEmpty()){
            throw new IllegalArgumentException(ApplicationConstants.CATEGORY_NAME_CANNOT_BE_EMPTY_MESSAGE);
        }
        if(category.getDescription() == null || category.getDescription().isEmpty()){
            throw new IllegalArgumentException(ApplicationConstants.CATEGORY_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE);
        }
        if(category.getName().length() > ApplicationConstants.NAME_MAX_LENGTH){
            throw new IllegalArgumentException(ApplicationConstants.NAME_CATEGORY_LENGTH_MESSAGE);
        }
        if(category.getDescription().length() > ApplicationConstants.DESCRIPTION_CATEGORY_MAX_LENGTH){
            throw new IllegalArgumentException(ApplicationConstants.DESCRIPTION_CATEGORY_LENGTH_MESSAGE);
        }
    }
}