/*package com.chores.chores.clients;

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
    //A service that calls another service (chores)
    //GET /chore/{id}
    // --> Chore

    //endpoint, where to call, where the service lives
    private final String restServiceUrl;

    //remote call (build a HTTP and get a response back)
    private final RestTemplate restTemplate;

    public ChoresClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("http://localhost:8080") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    //this Chore comes from the chores service
    //need to call this function to get the list of chores
    public ChoresDTO externalResolve(Long id) {
        String url = restServiceUrl + "/chore/" + id;

        //do a call to the extern service. wish to map the response to my own understanding of what the object is
        ResponseEntity<ChoreDTO> response = null;

        try {
            response = restTemplate.getForEntity(url, ChoreDTO.class);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return response.getBody();
    }
}
*/
