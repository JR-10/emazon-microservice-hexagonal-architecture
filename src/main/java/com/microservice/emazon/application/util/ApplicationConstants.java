package com.microservice.emazon.application.util;


public class ApplicationConstants {

    public static final int VALUE_ZERO = 0;
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final String NAME_NOT_NULL = "The name field cannot be null";
    public static final String DESCRIPTION_NOT_NULL = "The description field cannot be null";


    public static final String NAME_CATEGORY_REQUIRED_MESSAGE = "Category name is required";
    public static final String NAME_CATEGORY_LENGTH_MESSAGE = "The category name must be between " + NAME_MIN_LENGTH + " and "+NAME_MAX_LENGTH+" characters";
    public static final int DESCRIPTION_CATEGORY_MAX_LENGTH = 90;
    public static final String DESCRIPTION_CATEGORY_REQUIRED_MESSAGE = "category description is required";
    public static final String DESCRIPTION_CATEGORY_LENGTH_MESSAGE = "The category description must be between " + DESCRIPTION_MIN_LENGTH + " and "+DESCRIPTION_CATEGORY_MAX_LENGTH+" characters";
    public static final String NO_CATEGORIES_CREATED_MESSAGE = "No categories created";
    public static final String CATEGORY_NAME_ALREADY_EXISTS_MESSAGE = "The category name already exists";
    public static final String SUCCESS_CREATED_CATEGORY_MESSAGE = "You have successfully created the category ";
    public static final String CATEGORY_NAME_CANNOT_BE_EMPTY_MESSAGE = "The category name cannot be empty or null";
    public static final String CATEGORY_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE = "The category description cannot be empty or null";


    public static final String BRAND_NAME_ALREADY_EXISTS_MESSAGE = "The brand name already exists";
    public static final String NAME_BRAND_REQUIRED_MESSAGE = "Brand name is required";
    public static final String NAME_BRAND_LENGTH_MESSAGE = "The brand name must be between " + NAME_MIN_LENGTH + " and "+NAME_MAX_LENGTH+" characters";
    public static final String DESCRIPTION_BRAND_REQUIRED_MESSAGE = "Brand description is required";
    public static final int DESCRIPTION_BRAND_MAX_LENGTH = 120;
    public static final String DESCRIPTION_BRAND_LENGTH_MESSAGE = "The brand description must be between " + DESCRIPTION_MIN_LENGTH + " and "+DESCRIPTION_BRAND_MAX_LENGTH+" characters";
    public static final String BRAND_NAME_CANNOT_BE_EMPTY_MESSAGE = "The brand name cannot be empty or null";
    public static final String BRAND_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE = "The brand description cannot be empty or null";
    public static final String SUCCESS_CREATED_BRAND_MESSAGE = "You have successfully created the brand ";
    public static final String BRAND_NOT_FOUND_MESSAGE = "The brand was not found";
    public static final String BRAND_ID_NOT_NULL_MESSAGE = "The brand ID cannot be null";

    public static final String NAME_ARTICLE_REQUIRED_MESSAGE = "Article name is required";
    public static final String NAME_ARTICLE_LENGTH_MESSAGE = "The article name must be between " + NAME_MIN_LENGTH + " and "+NAME_MAX_LENGTH+" characters";
    public static final String DESCRIPTION_ARTICLE_REQUIRED_MESSAGE = "Article description is required";
    public static final int DESCRIPTION_ARTICLE_MAX_LENGTH = 120;
    public static final String DESCRIPTION_ARTICLE_LENGTH_MESSAGE = "The article description must be between " + DESCRIPTION_MIN_LENGTH + " and "+DESCRIPTION_BRAND_MAX_LENGTH+" characters";

    public static final String QUANTITY_ARTICLE_NEGATIVE_MESSAGE = "The amount cannot be negative";
    public static final String PRICE_ARTICLE_NEGATIVE_MESSAGE = "The price cannot be negative";

    public static final String QUANTITY_ARTICLE_REQUIRED_MESSAGE = "Article quantity is required";
    public static final String PRICE_ARTICLE_REQUIRED_MESSAGE = "Article price is required";
    public static final String BRAND_ARTICLE_REQUIRED_MESSAGE = "Article brand id is required";
    public static final String CATEGORY_ARTICLE_REQUIRED_MESSAGE = "Article category IDs are is required";
    public static final int CATEGORY_ID_MIN_LENGTH = 1;
    public static final int CATEGORY_ID_MAX_LENGTH = 3;
    public static final String CATEGORY_ID_LENGTH_MESSAGE = "Category IDs must be between " + CATEGORY_ID_MIN_LENGTH + " and "+CATEGORY_ID_MAX_LENGTH;
    public static final String CATEGORY_ID_UNIQUE_MESSAGE = "Category IDs must be unique";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "The categories was not found";

    public static final String ARTICLE_NAME_ALREADY_EXISTS_MESSAGE = "The article name already exists";
    public static final String ARTICLE_NAME_CANNOT_BE_EMPTY_MESSAGE = "The article name cannot be empty or null";
    public static final String ARTICLE_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE = "The article description cannot be empty or null";
    public static final String SUCCESS_CREATED_ARTICLE_MESSAGE = "You have successfully created the article ";
}

