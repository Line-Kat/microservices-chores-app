package com.chores.reward.service;

import com.chores.reward.eventdriven.RewardEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardService {
    public void updateBalance(RewardEvent message) {
        // receives message (object) from rabbitMQ
        // childUuid, List<choreUuids>
        // sync communication
    }


    public void updateSavingGoal(RewardEvent message) {
        // with sync communication // childUuid - get if savingGoal == null?
    }

    /*
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
