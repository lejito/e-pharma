package com.lejito.epharma.error;

import org.springframework.http.HttpStatus;

public abstract class CustomError extends RuntimeException {
    private HttpStatus status;

    CustomError(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    CustomError(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
