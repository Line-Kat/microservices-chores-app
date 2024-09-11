package com.chores.chores.service;

import com.chores.chores.DTO.ChildDTO;
import com.chores.chores.clients.ChoresClient;
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

    private final ChoresClient choresClient;

    @Override
    public Child addChoreToChild(Chore chore) {
        ChildDTO childDTO = choresClient.externalResolve(1L);
        Child child = childDTO.convertToChild();
        child.listOfChores.add(chore);
        return child;
    }
}
