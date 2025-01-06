package org.example.dma6m6beadando.controller;

import org.example.dma6m6beadando.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        ErrorDto errorDto = new ErrorDto(500, LocalDateTime.now(), e.getMessage());

        return new ResponseEntity<>(errorDto.msg(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
