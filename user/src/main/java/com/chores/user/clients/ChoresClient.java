package com.chores.user.clients;

import com.chores.user.DTO.ChoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Service
public class ChoresClient {
    // A service that calls another service (chores)

    // endpoint, where to call, where the service lives
    private final String restServiceUrl;

    // remote call (build an HTTP and get a response back)
    private final RestTemplate restTemplate;

    public ChoresClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("http://localhost:8080") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    // this Chore comes from the chores service
    // need to call this function to get the list of chores
    public ChoreDTO externalResolve(UUID choreUuid) {
        String url = restServiceUrl + "/chore/" + choreUuid;

        ResponseEntity<ChoreDTO> response;

        try {
            response = restTemplate.getForEntity(url, ChoreDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return response.getBody();
    }
}
