package com.chores.childchore.clients;

import com.chores.childchore.DTO.ChoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ChoresClient {

    // Calls the service Chores

    private final String restServiceUrl;
    private final RestTemplate restTemplate;

    public ChoresClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${chores.service.url}") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    // Method to get a chore from the service chore if it exits
    public Optional<ChoreDTO> getChore(UUID choreUuid) {
        String url = restServiceUrl + "/chore/" + choreUuid;
        ResponseEntity<ChoreDTO> response;

        try {
            response = restTemplate.getForEntity(url, ChoreDTO.class);
        } catch (Exception e) {
            log.error("Could not get chore: ", e);
            return Optional.empty();
        }
        return Optional.ofNullable(response.getBody());
    }
}