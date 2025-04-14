package com.lejito.epharma.security;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.lejito.epharma.error.UnauthorizedError;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationHandler extends BaseVerificationHandler {
    @Override
    public void handle(RequestContext context) {
        HttpServletRequest request = context.getRequest();

        if (shouldSkipValidation(request)) {
            System.out.println("AUTH: Skipping authentication for this request.");
            next(context);
            return;
        }


        if (!isAuthenticated(context)) {
            throw new UnauthorizedError("AUTH: Authentication failed");
        }
        next(context);
    }

    private boolean isAuthenticated(RequestContext context) {
        System.out.println("AUTH: Checking authentication...");
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