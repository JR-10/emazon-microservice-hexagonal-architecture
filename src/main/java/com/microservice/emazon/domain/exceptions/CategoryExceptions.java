package com.microservice.emazon.domain.exceptions;

/*
* Clase para manejar las excepciones de las categorias
* */
public class CategoryExceptions {

    private CategoryExceptions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
    * exception para cuando el nombre de la categoria ya existe
    * */
    public static class CategoryNameAlreadyExistsException extends RuntimeException {
        public CategoryNameAlreadyExistsException(String message) {
            super(message);
        }
    }

    /*
    * exception para cuando la categoria no existe
    * */
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

}