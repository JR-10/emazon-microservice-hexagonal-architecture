// TODO: 4-Create the BrandHandler class that implements IBrandHandler interface
package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.handler.IBrandHandler;
import com.microservice.emazon.application.mapper.IBrandDtoMapper;
import com.microservice.emazon.domain.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final IBrandDtoMapper brandMapper;

    @Override
    public void saveBrand(BrandRequestDto brandRequestDto) {
        brandServicePort.saveBrand(brandMapper.brandDtoToBrand(brandRequestDto));
    }
}
