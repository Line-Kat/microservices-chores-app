package com.chores.user.service;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.SavingGoalDTO;
import com.chores.user.clients.RewardClient;
import com.chores.user.model.Child;
import com.chores.user.model.Parent;
import com.chores.user.repository.ChildRepository;
import com.chores.user.repository.ParentRepository;
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
    private final ParentRepository parentRepository;

    public Optional<Child> findChildByUuid(UUID childUuid) {

        return childRepository.findChildByUuid(childUuid);
    }

    public Child createChild(Child child, UUID parentUuid) {
        return childRepository.findChildByUuid(child.getChildUuid())
                .orElseGet(() -> doCreateChild(child, parentUuid));
    }

    private Child doCreateChild(Child child, UUID parentUuid) {
        Parent parent = parentService.findParentByUuid(parentUuid).orElseThrow();
        child.setParent(parent);
        parent.getChildren().add(child);

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

    public BalanceDTO parentUpdateBalance(BalanceDTO balanceDTO) {
        return rewardClient.parentUpdateBalance(balanceDTO);
    }


}
