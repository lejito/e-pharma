package com.lejito.epharma.security;

import org.springframework.stereotype.Component;

@Component
public class VerificationChain {
    private final VerificationHandler chain;

    public VerificationChain(ProtectionHandler protectionHandler, SanitizationHandler sanitizationHandler,
            AuthenticationHandler authHandler, CacheHandler cacheHandler) {
        protectionHandler.setNext(sanitizationHandler);
        sanitizationHandler.setNext(authHandler);
        authHandler.setNext(cacheHandler);
        this.chain = protectionHandler;
    }

    public void process(RequestContext context) {
        chain.handle(context);
    }
}