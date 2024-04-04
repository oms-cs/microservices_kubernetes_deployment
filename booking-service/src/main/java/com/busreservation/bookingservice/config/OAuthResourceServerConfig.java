package com.busreservation.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuthResourceServerConfig {

    private static final String[] WHITE_LIST_URLS = {
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrfSpec -> csrfSpec.disable())
                .cors(corsSpec -> corsSpec.disable())
                .authorizeHttpRequests(authorizer -> {
                    authorizer.requestMatchers(WHITE_LIST_URLS).permitAll();
                    authorizer.anyRequest().authenticated();
                })
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()))
                .build();
    }

}
