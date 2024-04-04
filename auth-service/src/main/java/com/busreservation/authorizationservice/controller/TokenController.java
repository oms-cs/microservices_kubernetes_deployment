package com.busreservation.authorizationservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Base64;

@RestController
public class TokenController {

    public static RestClient restClient;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public static String clientId = "client";
    public static String clientSecret = "secret";
    public static String redirectUri = "http://localhost:9000/oauth/callback";

    public static ObjectMapper objectMapper = new ObjectMapper();

    public TokenController(RestClient.Builder builder, PasswordEncoder passwordEncoder){
        this.restClient = builder.build();
        this.passwordEncoder = passwordEncoder;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String publicEndpoint() {
        return "This is a Public Endpoint";
    }

    @GetMapping("/oauth/callback")
    public String oAuthCallBack(String code) throws JsonMappingException, JsonProcessingException {
        System.out.println("code == "+code);
        var creds = Base64.getEncoder().encodeToString((clientId + ":"  + clientSecret).getBytes());
        var payload = new LinkedMultiValueMap<String, String>();
        payload.add("code", code);
        payload.add("grant_type", "authorization_code");
        payload.add("redirect_uri", redirectUri);

        var response = this.restClient
                .post()
                .uri("http://localhost:9000/oauth2/token")
                .header("Authorization", "Basic "+creds)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(payload)
                .retrieve()
                .body(String.class);

        System.out.println("/n/n response "+response);

        var parsedResponse = objectMapper.readValue(response, TokenResponse.class);

        System.out.println("/n/n 📦 response  "+parsedResponse);

        var parts = parsedResponse.accessToken.split("\\.");

        var jwtPayload = Base64.getDecoder().decode(parts[1]);

        System.out.println("\n\n 🚀 jwtPayload  "+new String(jwtPayload));
        return new String(jwtPayload);
    }

    record TokenResponse(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("id_token") String idToken) {

    }

}
