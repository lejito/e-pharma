package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class ConflictError extends CustomError {
    public ConflictError(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
