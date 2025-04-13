package com.lejito.epharma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.error.CustomError;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseDTO> handleResponseStatusException(ResponseStatusException e) {
        ResponseDTO response = new ResponseDTO(false, e.getReason(), null, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof CustomError) {
            status = ((CustomError) e).getStatus();
        }

        ResponseDTO response = new ResponseDTO(false, e.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }
}
