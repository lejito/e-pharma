package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class InternalServerError extends CustomError {
    public InternalServerError(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
