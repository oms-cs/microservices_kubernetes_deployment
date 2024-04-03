package com.airy.ecom.authorizationservice.config;


import com.airy.ecom.authorizationservice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private static UserRegistrationService userRegistrationService;

    private static final Integer ENCODER_STRENGTH = 10;
    private static final String[] WHITE_LIST_URIS = {"/api/v1/auth/**","/oauth/callback","/oauth2/token"};
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(ENCODER_STRENGTH);
    }

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(authorizer -> {
                    authorizer.requestMatchers(WHITE_LIST_URIS).permitAll();
                    authorizer.anyRequest().authenticated();
                })
                .cors(customiser -> customiser.disable());
        return http.build();
    }
}
