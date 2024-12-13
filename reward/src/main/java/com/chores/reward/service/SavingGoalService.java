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

    // Method to create a child's saving goal
    public SavingGoal createSavingGoal(SavingGoal savingGoal) {
        // Check if saving goal exists
        return savingGoalRepository.findSavingGoalByChildUuid(savingGoal.getChildUuid())
                // If not, create a saving goal
                .orElseGet(() -> doCreateSavingGoal(savingGoal));
    }

    private SavingGoal doCreateSavingGoal(SavingGoal savingGoal) {
        return savingGoalRepository.save(savingGoal);
    }

    // Method to get a child's saving goal
    public Optional<SavingGoal> getSavingGoal(UUID childUuid) {
        return savingGoalRepository.findSavingGoalByChildUuid(childUuid);
    }

    // Method to update a SavingGoal (when the list of today's chores are completed)
    public void updateSavingGoal(UUID childUuid, Long value) {
        // Check if the child has a saving goal and set the new value
        Optional<SavingGoal> savingGoalByChildUuid = savingGoalRepository.findSavingGoalByChildUuid(childUuid);
        savingGoalByChildUuid
                .ifPresent(savingGoal -> {
                    savingGoal.setSavingGoalValue(savingGoal.getSavingGoalValue() - value);
                    savingGoalRepository.save(savingGoal);
                });
    }
}