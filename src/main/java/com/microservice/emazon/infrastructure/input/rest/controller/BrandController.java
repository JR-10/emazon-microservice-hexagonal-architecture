// TODO: 3 Tentativo
package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/brand")
@RequiredArgsConstructor
public class BrandController {


    private final IBrandHandler brandHandler;

    @Operation(summary = "Add a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content)
    })
    @PostMapping("/addBrand")
    public ResponseEntity<String> createBrand(@RequestBody BrandRequestDto brandRequestDto){
        brandHandler.saveBrand(brandRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ha creado exitosamente la marca " + brandRequestDto.getName());
    }


}
