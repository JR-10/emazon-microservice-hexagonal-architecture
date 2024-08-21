package com.microservice.emazon.application.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseDto {

    private String errorName;
    private String errorDescription;
    private HttpStatus statusCode;
}
