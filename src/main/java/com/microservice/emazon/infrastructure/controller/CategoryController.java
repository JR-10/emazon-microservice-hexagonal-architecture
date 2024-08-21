package com.microservice.emazon.infrastructure.controller;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryHandler categoryHandler;


    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return ResponseEntity.ok().body(categoryHandler.getAllCategories());
    }

    @PostMapping("/addCategory")
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryHandler.saveCategory(categoryRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Ha creado exitosamente la categoría " +categoryRequestDto.getName());
    }

    @GetMapping("/getCategories/{ordering}")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@PathVariable String ordering, Pageable pageable){
        return ResponseEntity.ok().body(categoryHandler.getCategories(ordering,pageable).getContent());
    }
}
