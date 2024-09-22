package com.chores.user.service;

import com.chores.user.DTO.ChoreDTO;
import com.chores.user.clients.UserClient;
import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
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

    public Child addChoreToChild(Child child, Long id) {
        ChildChore childChore = new ChildChore();
        ChoreDTO choreDTO = userClient.externalResolve(id);
        Child newChild = childRepository.findChildByUuid(child.getChildUuid()).get();

        childChore.setChoreId(choreDTO.getChoreId());
        childChore.setChildChoreUuid(UUID.randomUUID());
        childChore.setChild(newChild);
        childChore.setChoreId(choreDTO.getChoreId());

        childChoreRepository.save(childChore);

        return childRepository.findChildByUuid(child.getChildUuid()).orElseGet(null);
    }
}
