package com.vinay.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
