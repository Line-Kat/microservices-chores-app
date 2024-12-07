package com.chores.user.clients;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.SavingGoalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RewardClient {
    // A service that calls another service

    // Endpoint, where to call, where the service live
    private final String restServiceUrl;

    // Remote call (build an HTTP and get a response back)
    private final RestTemplate restTemplate;

    public RewardClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${round.client.host}") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    // This Reward comes from the Reward service
    // Need to call this function to get a child's balance
    public BalanceDTO createBalance(UUID childUuid) {
        String url = restServiceUrl + "/reward/balance/" + childUuid;

        ResponseEntity<BalanceDTO> response;

        try {
            BalanceDTO requestBody = new BalanceDTO();
            requestBody.setChildUuid(childUuid);
            response = restTemplate.postForEntity(url, requestBody, BalanceDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return response.getBody();
    }

    public Optional<BalanceDTO> getBalance(UUID childUuid) {
        String url = restServiceUrl + "/reward/balance/" + childUuid;

        ResponseEntity<BalanceDTO> response;

        try {
            response = restTemplate.getForEntity(url, BalanceDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.ofNullable(response.getBody());

        // return response.getBody();
    }

    public Optional<SavingGoalDTO> getSavingGoal(UUID childUuid) {
        String url = restServiceUrl + "/reward/goal/" + childUuid;

        ResponseEntity<SavingGoalDTO> response;

        try {
            response = restTemplate.getForEntity(url, SavingGoalDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.ofNullable(response.getBody());
    }

    public SavingGoalDTO createSavingGoal(SavingGoalDTO savingGoalDTO) {
        String url = restServiceUrl + "/reward/goal";

        ResponseEntity<SavingGoalDTO> response;

        try {
            SavingGoalDTO requestBody = new SavingGoalDTO();
            requestBody.setChildUuid(savingGoalDTO.getChildUuid());
            requestBody.setSavingGoalName(savingGoalDTO.getSavingGoalName());
            requestBody.setSavingGoalValue(savingGoalDTO.getSavingGoalValue());

            response = restTemplate.postForEntity(url, requestBody, SavingGoalDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return response.getBody();
    }

    public BalanceDTO parentUpdateBalance(UUID childUuid, BalanceDTO balanceDTO) {
        String url = restServiceUrl + "/reward/balance/update/" + childUuid ;
        ResponseEntity<BalanceDTO> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(balanceDTO), BalanceDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return response.getBody();
    }
}
