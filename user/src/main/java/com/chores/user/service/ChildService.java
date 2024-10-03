package com.chores.user.service;

import com.chores.user.DTO.ChoreDTO;
import com.chores.user.clients.ChoresClient;
import com.chores.user.eventdriven.RewardEventPublisher;
import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
import com.chores.user.model.ChildChoreStatus;
import com.chores.user.repository.ChildChoreRepository;
import com.chores.user.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@ToString
public class ChildService {

    private final ChildRepository childRepository;
    private final ParentService parentService;
    private final ChoresClient userClient;
    private final ChildChoreRepository childChoreRepository;
    private final RewardEventPublisher rewardEventPublisher;

    public Optional<Child> findChildByUuid(UUID childUuid) {
        return childRepository.findChildByUuid(childUuid);
    }

    public Child createChild(Child child, UUID parentUuid) {
        child.setParent(parentService.findParentByUuid(parentUuid).get());
        return childRepository.save(child);
    }

    public ChildChore addChoreToChild(UUID childUuid, UUID choreUuid,UUID childChoreUuid, Date date, ChildChoreStatus status) {

        //validating that the chore exists
        ChoreDTO choreDTO = userClient.externalResolve(choreUuid);
        if(choreDTO == null) {
            System.out.println("The chore doesn't exist");
            return null;
        }

        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreUuid);
        childChore.setChild(findChildByUuid(childUuid).get());
        childChore.setChoreUuid(choreUuid);
        childChore.setDate(date);
        childChore.setStatus(status);

        return childChoreRepository.save(childChore);
    }

    // updateChildChore - changes the status of chore completed
    // checks if all items in a list is completed for that day date.now == the date of todays list --> rewardEventPublisher.publishRewardEventString();
}
