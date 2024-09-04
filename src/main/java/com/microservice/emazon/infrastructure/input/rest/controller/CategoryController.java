package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.application.handler.ICategoryHandler;
import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Clase para manejar las operaciones de categoría en la capa de controlador
* */
@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    // inyectamos el manejador de categoría
    private final ICategoryHandler categoryHandler;


    /*
    * Metodo para obtener todas las categorías
    * */
    @Operation(summary = "Get all the categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categories returned",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return ResponseEntity.ok().body(categoryHandler.getAllCategories());
    }


    /*
    * Metodo para guardar una categoría
    * */
    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping("/addCategory")
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        categoryHandler.saveCategory(categoryRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(ApplicationConstants.SUCCESS_CREATED_CATEGORY_MESSAGE + categoryRequestDto.getName());
    }


    /*
    * Metodo para obtener las categorías paginadas
    * */
    @GetMapping("/getAllCategoriesPagination")
    public ResponseEntity<Pagination<CategoryResponseDto>> getPagination(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "name", required = false) String nameFilter,
            @RequestParam(defaultValue = "true", required = false) boolean ascending
    )  {
    Pagination<CategoryResponseDto> listpagination = categoryHandler.getAllCategoriesPagination(new PaginationUtil(pageNumber, pageSize, nameFilter, ascending));
        return ResponseEntity.ok().body(listpagination);
    }

}
