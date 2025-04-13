package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class BadRequestError extends CustomError {
    public BadRequestError(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
