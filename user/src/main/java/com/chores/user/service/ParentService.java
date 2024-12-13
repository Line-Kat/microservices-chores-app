package com.chores.user.service;

import com.chores.user.model.Parent;
import com.chores.user.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParentService{

    private final ParentRepository parentRepository;

    // Method to create parent
    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

    // Method to find a parent
    public Optional<Parent> findParentByUuid(UUID parentUuid) {
        return parentRepository.findByParentUuid(parentUuid);
    }
}
