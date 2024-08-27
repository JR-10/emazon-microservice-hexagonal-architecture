// TODO: 3 - Define the interface IBrandHandler with the method saveBrand(BrandRequestDto brandRequestDto)
package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.BrandRequestDto;

public interface IBrandHandler {

    void saveBrand(BrandRequestDto brandRequestDto);
}
