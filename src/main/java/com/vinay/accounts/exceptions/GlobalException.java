package com.vinay.accounts.exceptions;


import com.vinay.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerIsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerIsNotFound(CustomerIsNotFoundException ex, WebRequest request) {

        ErrorResponseDto responseDto=ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex, WebRequest request) {

        ErrorResponseDto responseDto=ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomExceptionHandler(RuntimeException ex, WebRequest request) {

        ErrorResponseDto responseDto=ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }


}
