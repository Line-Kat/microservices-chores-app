package com.chores.user.service;

import com.chores.user.model.Child;
import com.chores.user.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final ParentService parentService;

    public Optional<Child> findChildByUuid(UUID uuid) {
        return childRepository.findChildByUuid(uuid);
    }

    public Child createChild(Child child, UUID parentId) {
        child.setParent(parentService.findParentByUuid(parentId).get());
        return childRepository.save(child);
    }
}
