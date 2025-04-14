package com.lejito.epharma.security;

import org.springframework.stereotype.Component;

@Component
public class SanitizationHandler extends BaseVerificationHandler {
    @Override
    public void handle(RequestContext context) {
        sanitizeInput(context);
        next(context);
    }

    private void sanitizeInput(RequestContext context) {
        // Lógica de sanitización de datos
        System.out.println("SANITIZATION: Sanitizing input data...");
    }
}