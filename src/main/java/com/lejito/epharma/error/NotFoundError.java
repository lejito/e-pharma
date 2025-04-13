package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class NotFoundError extends CustomError {
    public NotFoundError(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
