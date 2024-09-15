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

    /*
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Optional<Parent> getParentBy(Long id) {
        return parentRepository.findById(id);
    }

    */

    public Optional<Parent> findParentByUuid(UUID uuid) {
        return parentRepository.findByParentUuid(uuid);
    }

    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

}

