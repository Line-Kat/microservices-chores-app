package com.chores.user.service;

import com.chores.user.model.Parent;
import com.chores.user.repository.ParentRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Parent getParentById(Long id) {
        return parentRepository.findById(id).orElse(null);
    }

    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

}

