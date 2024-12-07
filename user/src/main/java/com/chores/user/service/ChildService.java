package com.chores.user.service;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.SavingGoalDTO;
import com.chores.user.clients.RewardClient;
import com.chores.user.model.Child;
import com.chores.user.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ToString
public class ChildService {

    private final ChildRepository childRepository;
    private final ParentService parentService;
    private final RewardClient rewardClient;

    public Optional<Child> findChildByUuid(UUID childUuid) {

        return childRepository.findChildByUuid(childUuid);
    }

    public Child createChild(Child child, UUID parentUuid) {
        child.setParent(parentService.findParentByUuid(parentUuid).get());

        rewardClient.createBalance(child.getChildUuid());

        return childRepository.save(child);
    }

    public Optional<BalanceDTO> getBalance(UUID childUuid) {
        return rewardClient.getBalance(childUuid);
    }

    public Optional<SavingGoalDTO> getSavingGoal(UUID childUuid) {
        return rewardClient.getSavingGoal(childUuid);
    }

    public SavingGoalDTO createSavingGoal(SavingGoalDTO savingGoalDTO) {
        return rewardClient.createSavingGoal(savingGoalDTO);
    }

    public BalanceDTO parentUpdateBalance(UUID childUuid, BalanceDTO balanceDTO) {
        return rewardClient.parentUpdateBalance(childUuid, balanceDTO);
    }


}
