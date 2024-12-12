package com.chores.reward.service;

import com.chores.reward.DTO.ChildChoreDTO;
import com.chores.reward.eventdriven.RewardEvent;
import com.chores.reward.model.Balance;
import com.chores.reward.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final SavingGoalService savingGoalService;

    // Method create a child's balance
    public Balance addBalanceToChild(UUID childUuid) {
        // Check if the child has a balance, if not create a balance
        if (balanceRepository.findBalanceByChildUuid(childUuid).isEmpty()) {
            Balance balance = new Balance();
            balance.setBalanceUuid(UUID.randomUUID());
            balance.setChildUuid(childUuid);
            balance.setBalanceValue(0);

            return balanceRepository.save(balance);
        }

        return null;
    }

    // Method to get a child's balance
    public Optional<Balance> getBalance(UUID childUuid) {
        return balanceRepository.findBalanceByChildUuid(childUuid);
    }

    // Method for parent to update a child's chore
    public Balance parentUpdateBalance(Balance balance) {
        // Retrieve balance from the database, if it doesn't exist create a new balance
        Balance tempBalance = balanceRepository.findBalanceByChildUuid(balance.getChildUuid())
                .orElseGet(() -> {balance.setBalanceUuid(UUID.randomUUID());
                return balance;});

        tempBalance.setBalanceValue(balance.getBalanceValue());

        return balanceRepository.save(tempBalance);
    }

    // Method for updating a child's balance based on a message received from RabbitMQ
    // The message received is containing today's date and a list of a child's chores of today
    public void updateBalance(RewardEvent message) {
        // Get the list of chores from the message
        List<ChildChoreDTO> listOfChildChoreDTO = message.getChildChoreDateDTO().getListOfChildChoreDTO();

        // Get the childUuid from the message
        UUID childUuid = message.getChildChoreDateDTO().getListOfChildChoreDTO().get(0).getChildUuid();

        // Retrieve the child's balance from the database
        Balance balance = balanceRepository.findBalanceByChildUuid(childUuid).orElseThrow();

        // Calculate the total earned from the list of completed chores
        int earnedValue = listOfChildChoreDTO.stream()
                .mapToInt(ChildChoreDTO::getValue)
                .sum();

        // Set the new balance
        balance.setBalanceValue(balance.getBalanceValue() + earnedValue);

        // Save the balance object
        balanceRepository.save(balance);

        // Call the SavingGoalService
        savingGoalService.updateSavingGoal(childUuid, (long) earnedValue);
    }
}
