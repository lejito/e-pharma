package com.lejito.epharma.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class ResponseDTO {
    private boolean ok;
    private String message;
    private Object data;
    private HttpStatus statusCode;

    public ResponseDTO(boolean ok, String message, HttpStatus statusCode) {
        this.ok = ok;
        this.message = message;
        this.data = null;
        this.statusCode = statusCode;
    }
}
