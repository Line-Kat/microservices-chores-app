package com.chores.childchore.service;

import com.chores.childchore.eventdriven.RewardEventPublisher;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.model.ChildChoreStatus;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import com.chores.childchore.repository.ChildChoreRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ToString
public class ChildChoreService {
    private final RewardEventPublisher rewardEventPublisher;
    private final ChildChoreRepository childChoreRepository;

    public ChildChore addChoreToChild(UUID childUuid, UUID choreUuid, UUID childChoreUuid, Date date, ChildChoreStatus status) {

        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreUuid);
        childChore.setChildUuid(childUuid);
        childChore.setChoreUuid(choreUuid);
        childChore.setDate(date);
        childChore.setStatus(status);

        return childChoreRepository.save(childChore);
    }

    // updateChildChore - changes the status of chore completed
    // checks if all items in a list are completed for that day date.now == the date of today's list --> rewardEventPublisher.publishRewardEventString();
    public ChildChore updateChildChore(ChildChore childChore) {
        // get childchore from database, then change it
        // create a function to find all the chores to a child
        ChildChore tempChildChore = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();
        tempChildChore.setStatus(childChore.getStatus());

        checkStatusOfListOfChores(tempChildChore.getChildUuid());
        return childChoreRepository.save(tempChildChore);
    }

    private void checkStatusOfListOfChores(UUID childUuid) {
        List<ChildChore> listOfChores = childChoreRepository.findAllByChildUuid(childUuid);
        List<ChildChore> listSendToRabbitMQ = new ArrayList<>();

        for (ChildChore chore : listOfChores) {
            if (chore.getStatus() == ChildChoreStatus.COMPLETED) {
                listSendToRabbitMQ.add(chore);


                if(listOfChores.size() == listSendToRabbitMQ.size()) {
                    rewardEventPublisher.publishRewardEvent(listSendToRabbitMQ);
                }



                return;
            }
        }
    }
}
