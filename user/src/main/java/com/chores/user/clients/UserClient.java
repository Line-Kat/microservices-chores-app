package com.chores.user.clients;

import com.chores.user.DTO.ChoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserClient {
    //A service that calls another service (chores)
    //GET /chore/{id}
    // --> Chore

    //endpoint, where to call, where the service lives
    private final String restServiceUrl;

    //remote call (build an HTTP and get a response back)
    private final RestTemplate restTemplate;

    public UserClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("http://localhost:8080") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    //this Chore comes from the chores service
    //need to call this function to get the list of chores
    public ChoreDTO externalResolve(Long id) {
        String url = restServiceUrl + "/chore/" + id;

        //do a call to the extern service. wish to map the response to my own understanding of what the object is
        ResponseEntity<ChoreDTO> response;

        try {
            response = restTemplate.getForEntity(url, ChoreDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            //e.printStackTrace();
            return null;
        }

        return response.getBody();
    }
}
