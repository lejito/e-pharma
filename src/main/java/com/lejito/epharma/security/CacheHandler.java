package com.lejito.epharma.security;

import org.springframework.stereotype.Component;

@Component
public class CacheHandler extends BaseVerificationHandler {
    @Override
    public void handle(RequestContext context) {
        if (isCached(context)) {
            System.out.println("CACHE: Request is cached, skipping further processing.");
            return;
        }
        cacheRequest(context);
        next(context);
    }

    private boolean isCached(RequestContext context) {
        System.out.println("CACHE: Checking if request is cached...");
        return false;
    }

    private void cacheRequest(RequestContext context) {
        System.out.println("CACHE: Caching request...");
    }

}
