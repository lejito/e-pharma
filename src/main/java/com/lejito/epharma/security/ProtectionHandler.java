package com.lejito.epharma.security;

import org.springframework.stereotype.Component;

import com.lejito.epharma.error.UnauthorizedError;

@Component
public class ProtectionHandler extends BaseVerificationHandler {
    @Override
    public void handle(RequestContext context) {
        if (!validateRequest(context)) {
            throw new UnauthorizedError("PROTECTION: Request validation failed");
        }
        next(context);
    }

    private boolean validateRequest(RequestContext context) {
        System.out.println("PROTECTION: Validating request...");
        return true;
    }


}
