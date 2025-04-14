package com.lejito.epharma.security;

import jakarta.servlet.http.HttpServletRequest;

public class RequestContext {
    private final HttpServletRequest request;

    public RequestContext(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}