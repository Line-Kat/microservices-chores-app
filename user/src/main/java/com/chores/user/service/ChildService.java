package com.chores.user.service;

import com.chores.user.clients.ChoresClient;
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
    private final ChoresClient userClient; // endres til RewardClient

    public Optional<Child> findChildByUuid(UUID childUuid) {

        return childRepository.findChildByUuid(childUuid);
    }

    public Child createChild(Child child, UUID parentUuid) {
        child.setParent(parentService.findParentByUuid(parentUuid).get());

        // call Reward service to create a balance

        return childRepository.save(child);
    }
}
