package com.chores.user.service;

import com.chores.user.DTO.ChoreDTO;
import com.chores.user.clients.UserClient;
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
    private final UserClient userClient;
    private final ChildChoreRepository childChoreRepository;

    public Optional<Child> findChildByUuid(UUID uuid) {
        return childRepository.findChildByUuid(uuid);
    }

    public Child createChild(Child child, UUID parentId) {
        child.setParent(parentService.findParentByUuid(parentId).get());
        return childRepository.save(child);
    }

    public ChildChore addChoreToChild(UUID childUuid, UUID choreUuid,UUID childChoreUuid, Date date, ChildChoreStatus status) {

        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreUuid);
        childChore.setChild(findChildByUuid(childUuid).get());
        childChore.setChoreUuid(choreUuid);
        childChore.setDate(date);
        childChore.setStatus(status);

        return childChoreRepository.save(childChore);
    }
}
