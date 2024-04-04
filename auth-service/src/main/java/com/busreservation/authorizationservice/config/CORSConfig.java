package com.busreservation.authorizationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Value("${cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${cors.cors-config}")
    private String corsConfiguration;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsConfiguration).allowedMethods(allowedMethods.split(",")).allowedHeaders(allowedHeaders);
    }
}