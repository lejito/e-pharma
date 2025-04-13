package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class ForbbidenError extends CustomError {
    public ForbbidenError(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
