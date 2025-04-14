package com.lejito.epharma.security;

public abstract class BaseVerificationHandler implements VerificationHandler {
    private VerificationHandler next;

    @Override
    public void setNext(VerificationHandler next) {
        this.next = next;
    }

    protected void next(RequestContext context) {
        if (next != null) {
            next.handle(context);
        }
    }
}