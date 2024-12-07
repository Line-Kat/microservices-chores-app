package com.chores.reward.service;

import com.chores.reward.eventdriven.RewardEvent;
import com.chores.reward.model.Balance;
import com.chores.reward.model.SavingGoal;
import com.chores.reward.repository.BalanceRepository;
import com.chores.reward.repository.SavingGoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final SavingGoalRepository savingGoalRepository;
    private final SavingGoalService savingGoalService;

    // gets a call from User when a child is created
    public Balance addBalanceToChild(UUID childUuid) {
        // Check if the childUuid already exists
        if (balanceRepository.findBalanceByChildUuid(childUuid).isEmpty()) {
            Balance balance = new Balance();
            balance.setBalanceUuid(UUID.randomUUID());
            balance.setChildUuid(childUuid);
            balance.setBalanceValue(0);

            return balanceRepository.save(balance);
        }
        return null;
    }

    public Optional<Balance> getBalance(UUID childUuid) {
        return balanceRepository.findBalanceByChildUuid(childUuid);
    }

    public Balance parentUpdateBalance(Balance balance) {
        Balance tempBalance = balanceRepository.findBalanceByChildUuid(balance.getChildUuid())
                .orElseGet(() -> {balance.setBalanceUuid(UUID.randomUUID());
                return balance;});
        tempBalance.setBalanceValue(balance.getBalanceValue());
        return balanceRepository.save(tempBalance);
    }

    public void updateBalance(RewardEvent message) {
        // receives message (childUuid and a list of childChoreValues) from rabbitMQ

        Balance balance = balanceRepository.findBalanceByChildUuid(message.getChildUuid()).orElseThrow();
        int value = balance.getBalanceValue();

        for(int childChoreValue : message.getChildChoreValues()) {
            value += childChoreValue;
        }

        balance.setBalanceValue(value);

        balanceRepository.save(balance);

        if (savingGoalRepository.getSavingGoalByChildUuid(message.getChildUuid()).isPresent()) {
            savingGoalService.updateSavingGoal(message.getChildUuid(), (long) value);
        }
    }
}
