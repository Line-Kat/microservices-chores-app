package com.chores.chores.service;

import com.chores.chores.model.Child;
import com.chores.chores.model.Chore;
import com.chores.chores.repository.ChoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChoresServiceImpl implements ChoresService {
    private final ChoresRepository choresRepository;

    @Override
    public Chore createChore(Chore chore) {
        return choresRepository.save(chore);
    }

    @Override
    public Child addChoreToChild(Child child, Chore chore) {
        child.listOfChores.add(chore);
        return child;
    }
}
