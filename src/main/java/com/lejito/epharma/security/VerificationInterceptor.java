package com.lejito.epharma.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class VerificationInterceptor implements HandlerInterceptor {
    private final VerificationChain verificationChain;

    public VerificationInterceptor(VerificationChain verificationChain) {
        this.verificationChain = verificationChain;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            RequestContext context = new RequestContext(request);
            verificationChain.process(context);
        }

        return true;
    }
}