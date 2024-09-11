package com.chores.chores.clients;

import com.chores.chores.DTO.ChildDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChoresClient {
    //A service that calls another service (user)
    //GET /child/{id}
    // --> Child

    //endpoint, where to call, where the service lives
    private final String restServiceUrl;

    //remote call (build a HTTP and get a response back)
    private final RestTemplate restTemplate;

    public ChoresClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("http://localhost:8081") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    //this Child comes from the user service
    public ChildDTO externalResolve(Long id) {
        String url = restServiceUrl + "/child/" + id;

        //do a call to the extern service. wish to map the response to my own understanding of what the object is
        ResponseEntity<ChildDTO> response = null;

        try {
            response = restTemplate.getForEntity(url, ChildDTO.class);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return response.getBody();
    }
}
