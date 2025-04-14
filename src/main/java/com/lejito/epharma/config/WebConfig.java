package com.lejito.epharma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lejito.epharma.security.VerificationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private VerificationInterceptor verificationInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(verificationInterceptor).addPathPatterns("/**");
    }
}