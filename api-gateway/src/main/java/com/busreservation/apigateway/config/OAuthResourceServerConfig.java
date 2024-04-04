package com.busreservation.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class OAuthResourceServerConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/actuator/prometheus",
            "/eureka/**"
    };


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .csrf(csrfSpec -> csrfSpec.disable())
                .cors(corsSpec -> corsSpec.disable())
                .authorizeExchange(authorizer -> {
                    authorizer.pathMatchers(WHITE_LIST_URLS).permitAll();
                    authorizer.anyExchange().authenticated();
                })
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()))
                .build();
    }

}
