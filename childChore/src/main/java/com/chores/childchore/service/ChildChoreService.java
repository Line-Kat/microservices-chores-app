package com.chores.childchore.service;

import com.chores.childchore.eventdriven.RewardEventPublisher;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.model.ChildChoreStatus;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import com.chores.childchore.repository.ChildChoreRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@ToString
public class ChildChoreService {
    private final RewardEventPublisher rewardEventPublisher;
    private final ChildChoreRepository childChoreRepository;

    public ChildChore addChoreToChild(UUID childUuid, UUID choreUuid, UUID childChoreUuid, Date date, ChildChoreStatus status, int value) {

        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreUuid);
        childChore.setChildUuid(childUuid);
        childChore.setChoreUuid(choreUuid);
        childChore.setDate(date);
        childChore.setStatus(status);
        childChore.setValue(value);

        return childChoreRepository.save(childChore);
    }

    public List<ChildChore> getAllChores(UUID childUuid) {
        return childChoreRepository.findAllByChildUuid(childUuid);
    }

    public void deleteChildChore(ChildChore childChore) {
        ChildChore cc = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();
        childChoreRepository.deleteById(cc.getChildChoreId());
    }

    // updateChildChore - changes the status of chore completed
    // checks if all items in a list are completed for that day date.now == the date of today's list --> rewardEventPublisher.publishRewardEventString();
    public ChildChore updateChildChore(ChildChore childChore) {
        // get childchore from database, then change it
        ChildChore tempChildChore = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();
        tempChildChore.setStatus(childChore.getStatus());

        checkStatusOfListOfChores(tempChildChore.getChildUuid());
        return childChoreRepository.save(tempChildChore);
    }

    private void checkStatusOfListOfChores(UUID childUuid) {
        List<ChildChore> listOfChores = childChoreRepository.findAllByChildUuid(childUuid);
        List<Integer> listSendToRabbitMQ = new ArrayList<>();

        for (ChildChore chore : listOfChores) {
            if (chore.getStatus() == ChildChoreStatus.COMPLETED) {
                listSendToRabbitMQ.add(chore.getValue());
            }
        }

        if(listOfChores.size() == listSendToRabbitMQ.size()) {
            rewardEventPublisher.publishRewardEvent(childUuid, listSendToRabbitMQ);
        }
    }
}
