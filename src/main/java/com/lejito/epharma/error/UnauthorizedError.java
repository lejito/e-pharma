package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public class UnauthorizedError extends CustomError {
    public UnauthorizedError(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
