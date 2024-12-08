package com.chores.reward.service;

import com.chores.reward.model.SavingGoal;
import com.chores.reward.repository.SavingGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavingGoalService {
    private final SavingGoalRepository savingGoalRepository;

    public SavingGoal createSavingGoal(SavingGoal savingGoal) {
        savingGoal.setSavingGoalUuid(UUID.randomUUID());

        return savingGoalRepository.save(savingGoal);
    }

    public Optional<SavingGoal> getSavingGoal(UUID childUuid) {
        return savingGoalRepository.getSavingGoalByChildUuid(childUuid);
    }

    public void updateSavingGoal(UUID childUuid, Long value) {
        SavingGoal savingGoal = savingGoalRepository.getSavingGoalByChildUuid(childUuid).orElseThrow();
        savingGoal.setSavingGoalValue(savingGoal.getSavingGoalValue() - value);
        savingGoalRepository.save(savingGoal);
    }

}