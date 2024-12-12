package com.chores.user.service;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.ChangeInBalanceDTO;
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

    // CHILD

    // Method to create a child
    public Child createChild(Child child, UUID parentUuid) {
        // Check if child exists
        return childRepository.findChildByUuid(child.getChildUuid())
                // If not, create a child
                .orElseGet(() -> doCreateChild(child, parentUuid));
    }

    private Child doCreateChild(Child child, UUID parentUuid) {
        // Retrieve parent from the database
        Parent parent = parentService.findParentByUuid(parentUuid).orElseThrow();

        child.setParent(parent);

        // Add the child to the parent's list of children
        parent.getChildren().add(child);

        // Create a balance for the child by calling the Reward service
        rewardClient.createBalance(child.getChildUuid());

        return childRepository.save(child);
    }

    // Method to retrieve a child from the database
    public Optional<Child> findChildByUuid(UUID childUuid) {
        return childRepository.findChildByUuid(childUuid);
    }

    // BALANCE

    // Method to get a child's balance
    public Optional<BalanceDTO> getBalance(UUID childUuid) {
        return rewardClient.getBalance(childUuid);
    }

    // Method for parent to update a child's balance
    public BalanceDTO parentUpdateBalance(ChangeInBalanceDTO changeInBalance) {
        return rewardClient.parentUpdateBalance(changeInBalance);
    }

    public Optional<SavingGoalDTO> getSavingGoal(UUID childUuid) {
        return rewardClient.getSavingGoal(childUuid);
    }

    public SavingGoalDTO createSavingGoal(SavingGoalDTO savingGoalDTO) {
        return rewardClient.createSavingGoal(savingGoalDTO);
    }




}
