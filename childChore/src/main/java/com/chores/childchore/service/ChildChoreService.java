package com.chores.childchore.service;

import com.chores.childchore.eventdriven.RewardEventPublisher;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.model.ChildChoreStatus;
import com.chores.childchore.repository.ChildChoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@ToString
public class ChildChoreService {
    private final RewardEventPublisher rewardEventPublisher;
    private final ChildChoreRepository childChoreRepository;

    // Method to add a chore to a child
    public ChildChore addChoreToChild(ChildChore childChore) {
        return childChoreRepository.save(childChore);
    }

    // Method to get a list of all the chores of a child
    public List<ChildChore> getAllChores(UUID childUuid) {
        return childChoreRepository.findAllChoresByChildUuid(childUuid);
    }









    public void deleteChildChore(ChildChore childChore) {
        ChildChore cc = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();
        childChoreRepository.deleteById(cc.getChildChoreId());
    }

    // Method to update a childChore
    // If the status is updated, another method is called to check if all the chores of the day are completed
    public ChildChore updateChildChore(ChildChore childChore, String field) {
        // Get the childChore from the database
        ChildChore tempChildChore = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();

        // Checks what field to update
        switch (field) {
            case "status" -> {
                // Change the status of the childChore
                tempChildChore.setStatus(childChore.getStatus());

                // Calls this method to check if the list of today's chores are completed
                checkStatusOfListOfChores(tempChildChore.getChildUuid());
            }
            case "date" ->
                // Change the date of the childChore
                    tempChildChore.setDate(childChore.getDate());
            case "value" ->
                // Change the value of the childChore
                    tempChildChore.setValue(childChore.getValue());
        }

        return childChoreRepository.save(tempChildChore);
    }



    // Need to change RewardEvent to send a List of childChoreDate-objects
    private void checkStatusOfListOfChores(UUID childUuid) {

        List<ChildChore> listOfChores = childChoreRepository.findAllChoresByChildUuid(childUuid);

        List<Integer> listSendToRabbitMQ = new ArrayList<>();

        // I should send a list of a child's chores of the day (ChildChoreDateDTO)
        for (ChildChore chore : listOfChores) {
            if (chore.getStatus() == ChildChoreStatus.COMPLETED) {
                listSendToRabbitMQ.add(chore.getValue());
            }
        }

        if(listOfChores.size() == listSendToRabbitMQ.size() && !listSendToRabbitMQ.isEmpty()) {
            rewardEventPublisher.publishRewardEvent(childUuid, listSendToRabbitMQ);
        }
    }
}
