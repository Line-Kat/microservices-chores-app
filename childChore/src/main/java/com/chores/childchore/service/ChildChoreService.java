package com.chores.childchore.service;

import com.chores.childchore.DTO.ChildChoreDTO;
import com.chores.childchore.DTO.ChildChoreDateDTO;
import com.chores.childchore.eventdriven.RewardEventPublisher;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.model.ChildChoreStatus;
import com.chores.childchore.repository.ChildChoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

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

    // Method to add a chore to a child (create a ChildChore object)
    public ChildChore addChoreToChild(ChildChore childChore) {
        return childChoreRepository.save(childChore);
    }

    // Method to get a list of all the chores of a child
    public List<ChildChore> getAllChores(UUID childUuid) {
        return childChoreRepository.findAllChoresByChildUuid(childUuid);
    }

    // Method to update a childChore
    public ChildChore updateChildChore(ChildChore childChore, String field) {
        // Get the childChore from the database
        ChildChore tempChildChore = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();

        // Checks what field to update
        switch (field) {
            case "status" -> {
                // Change the status of the childChore
                tempChildChore.setStatus(childChore.getStatus());

                // Calls this method to check if the list of today's chores are completed
                checkStatusOfTheChoresOfToday(tempChildChore.getChildUuid());
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

    // Method to send a message to rabbitMQ if all the chores on today's list are completed
    private void checkStatusOfTheChoresOfToday(UUID childUuid) {
        // Get all the child's chores
        List<ChildChore> listOfChildChore = childChoreRepository.findAllChoresByChildUuid(childUuid);

        List<ChildChoreDTO> listSendToRabbitMQ = new ArrayList<>();

        // Add the completed chores to a new list
        for (ChildChore chore : listOfChildChore) {
            if (chore.getStatus() == ChildChoreStatus.COMPLETED) {
                listSendToRabbitMQ.add(mapToChildChoreDTO(chore));
            }
        }

        // Check if the list being sent to rabbitMQ is not empty and that all the chores on the list are completed
        if (!listSendToRabbitMQ.isEmpty() && listOfChildChore.size() == listSendToRabbitMQ.size()) {
            // Map to an object to send to rabbitMQ
            ChildChoreDateDTO childChoreDateDTO = new ChildChoreDateDTO(new Date(), listSendToRabbitMQ);

            rewardEventPublisher.publishRewardEvent(childChoreDateDTO);
        }
    }

    // Method to delete a chore from a child
    public void deleteChildChore(ChildChore childChore) {
        // Retrieve the childChore from the database to get its id
        ChildChore cc = childChoreRepository.findChildChoreByUuid(childChore.getChildChoreUuid()).orElseThrow();

        childChoreRepository.deleteById(cc.getChildChoreId());
    }

    // Mapping
    private ChildChoreDTO mapToChildChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus(), childChore.getValue());
    }
}
