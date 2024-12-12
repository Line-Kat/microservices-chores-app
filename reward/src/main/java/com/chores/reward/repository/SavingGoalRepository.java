package com.chores.reward.repository;

import com.chores.reward.model.SavingGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {
    Optional<SavingGoal> findSavingGoalByChildUuid(UUID childUuid);

}
