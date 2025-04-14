package com.lejito.epharma.security;

public interface VerificationHandler {
    void handle(RequestContext context);
    void setNext(VerificationHandler next);
}