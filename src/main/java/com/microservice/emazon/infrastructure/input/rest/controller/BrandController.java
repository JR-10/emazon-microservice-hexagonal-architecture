package com.microservice.emazon.infrastructure.input.rest.controller;

import com.microservice.emazon.application.dto.request.BrandRequestDto;
import com.microservice.emazon.application.dto.response.BrandResponseDto;
import com.microservice.emazon.application.handler.IBrandHandler;
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
    @PostMapping()
    public ResponseEntity<String> createBrand(@RequestBody @Valid BrandRequestDto brandRequestDto){
        brandHandler.saveBrand(brandRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationConstants.SUCCESS_CREATED_BRAND_MESSAGE + brandRequestDto.getNameBrand());
    }


    @Operation(summary = "Get all the brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All brands returned",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BrandResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<Pagination<BrandResponseDto>> getAllBrands(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "nameBrand", required = false) String nameFilter,
            @RequestParam(defaultValue = "true", required = false) boolean ascending
    )  {
    Pagination<BrandResponseDto> listBrandsPagination = brandHandler.getAllBrands(new PaginationUtil(pageNumber, pageSize, nameFilter, ascending));
        return ResponseEntity.ok().body(listBrandsPagination);
    }


}
