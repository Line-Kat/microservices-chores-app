package com.chores.reward.service;

import com.chores.reward.eventdriven.RewardEvent;
import com.chores.reward.model.Balance;
import com.chores.reward.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    // communicate with User
    // gets a call from User when a child is created
    public Balance addBalanceToChild(UUID childUuid) {
        Balance balance = new Balance();
        balance.setBalanceUuid(UUID.randomUUID());
        balance.setChildUuid(childUuid);
        balance.setBalanceValue(0);

        return balance;
    }

    public void updateBalance(RewardEvent message) {
        // receives message (childUuid and a list of childChoreValues) from rabbitMQ

        log.info("CHILDUUID: " + message.getChildUuid());
        log.info("VALUE: " + message.getChildChoreValues().get(0));

        Balance balance = new Balance();
        balance.setBalanceId(3L);
        balance.setBalanceUuid(UUID.randomUUID());
        balance.setChildUuid(message.getChildUuid());
        balance.setBalanceValue(0);
        // get the balance object from the database and update it

        log.info("CURRENT VALUE" + balance.getBalanceValue());

        //NB need to add an object first
        //Balance tempBalance = balanceRepository.findBalanceByChildUuid(message.getChildUuid()).orElseThrow();
        int value = balance.getBalanceValue();

        for(int childChoreValue : message.getChildChoreValues()) {
            value += childChoreValue;
        }

        balance.setBalanceValue(value);

        log.info("NEW VALUE: " + balance.getBalanceId() + balance.getBalanceUuid() + balance.getBalanceValue());

        balanceRepository.save(balance);
    }

    /*
    public void updateSavingGoal(RewardEvent message) {
        // with sync communication // childUuid - get if savingGoal == null?
    }

    // method so child can know its balance
    public Balance getBalance() {
        return Balance;
    }

    // method so child can know its savingGoal
    public SavingGoal getSavingGoal() {
        return SavingGoal;
    }

     */
}
