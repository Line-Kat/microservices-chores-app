package com.chores.user.service;

import com.chores.user.model.Child;
import com.chores.user.repository.ChildRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Optional<Child> getChildById(Long id) {
        return childRepository.findById(id);
    }

    public Child createChild(Child child) {
        return childRepository.save(child);
    }
}
