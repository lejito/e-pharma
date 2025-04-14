package com.lejito.epharma.security;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.lejito.epharma.error.UnauthorizedError;
import com.lejito.epharma.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationHandler extends BaseVerificationHandler {
    private AuthService authService;

    public AuthenticationHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void handle(RequestContext context) {
        HttpServletRequest request = context.getRequest();

        if (shouldSkipValidation(request)) {
            System.out.println("AUTH: Skipping authentication for this request.");
            next(context);
            return;
        }

        if (!isAuthenticated(context)) {
            System.out.println("AUTH: Authentication failed.");
            throw new UnauthorizedError("AUTH: Authentication failed");
        }
        next(context);
    }

    private boolean isAuthenticated(RequestContext context) {
        System.out.println("AUTH: Checking authentication...");
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("Authorization");
        if (token == null || !authService.validateToken(token)) {
            return false;
        }
        return true;
    }

    private boolean shouldSkipValidation(HttpServletRequest request) {
        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            SkipHandler annotation = handlerMethod.getMethodAnnotation(SkipHandler.class);
            if (annotation == null) {
                annotation = handlerMethod.getBeanType().getAnnotation(SkipHandler.class);
            }

            return annotation != null && annotation.value().equals(this.getClass());
        }
        return false;
    }
}