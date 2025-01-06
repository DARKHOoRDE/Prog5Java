package org.example.dma6m6beadando.Dto;


import java.time.LocalDateTime;


public record ErrorDto (
        int status,
        LocalDateTime time,
        String msg
){}
