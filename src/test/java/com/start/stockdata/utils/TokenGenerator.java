package com.start.stockdata.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
public class TokenGenerator {

    private static final String URL = "https://auth-service-vladify.herokuapp.com/api/auth/login";
    private static final String EMAIL = "vlad.danylovych@gmail.com";
    private static final String PASSWORD = "password";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new Gson();

    public static String getToken()  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();


        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", EMAIL);
        map.add("password", PASSWORD);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( URL, request , String.class );
        Optional<String> optionalResponseBody = Optional.ofNullable(response.getBody());

        if (optionalResponseBody.isPresent()){
            try {
                AuthenticationResponse authResponse = OBJECT_MAPPER.readValue(optionalResponseBody.get(), AuthenticationResponse.class);
                return authResponse.getToken();
            } catch (JsonProcessingException e) {
                log.error("Error while reading response from auth server", e);
                throw new RuntimeException("Error while reading response from auth server");
            }

        } else {
            log.error("Response from auth service is NULL");
            throw new RuntimeException("Response from auth service is NULL");
        }

    }


    @Getter
    @Setter
    private static class AuthenticationResponse {
        //@JsonAlias("id_token")
        private String token;


    }

}
