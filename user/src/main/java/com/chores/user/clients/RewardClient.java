package com.chores.user.clients;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.ChangeInBalanceDTO;
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
    // Calls the service Reward

    private final String restServiceUrl;
    private final RestTemplate restTemplate;

    public RewardClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${round.client.host}") final String restServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = restServiceUrl;
    }

    // BALANCE

    // Method to create a child's balance
    public BalanceDTO createBalance(UUID childUuid) {
        String url = restServiceUrl + "/reward/balance";
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

    // Method to get a child's balance
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
    }

    // Method for parent to update a child's balance
    public BalanceDTO parentUpdateBalance(ChangeInBalanceDTO changeInBalanceDTO) {
        String url = restServiceUrl + "/reward/balance/update";
        ResponseEntity<BalanceDTO> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(changeInBalanceDTO), BalanceDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return response.getBody();
    }

    // SAVING GOAL

    // Method to create a child's saving goal
    public SavingGoalDTO createSavingGoal(SavingGoalDTO savingGoalDTO) {
        String url = restServiceUrl + "/reward/goal";
        ResponseEntity<SavingGoalDTO> response;

        try {
            SavingGoalDTO requestBody = new SavingGoalDTO();
            requestBody.setChildUuid(savingGoalDTO.getChildUuid());
            requestBody.setSavingGoalUuid(savingGoalDTO.getSavingGoalUuid());
            requestBody.setSavingGoalName(savingGoalDTO.getSavingGoalName());
            requestBody.setSavingGoalValue(savingGoalDTO.getSavingGoalValue());

            response = restTemplate.postForEntity(url, requestBody, SavingGoalDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return response.getBody();
    }

    // Method to get a child's saving goal
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
}
