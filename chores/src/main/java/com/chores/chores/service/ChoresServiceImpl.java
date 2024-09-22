package com.chores.chores.service;

import com.chores.chores.model.Chore;
import com.chores.chores.repository.ChoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ChoresServiceImpl implements ChoresService {
    private final ChoresRepository choresRepository;
    //private final UserClient userClient;

    @Override
    public Chore createChore(Chore chore) {
        return choresRepository.save(chore);
    }

    @Override
    public List<Chore> getChores() {
        return choresRepository.findAll();
    }


    @Override
    public Optional<Chore> getChoreById(Long id) {
        return choresRepository.findById(id);
    }




    /*
    @Override
    public Child addChoreToChild(Chore chore, Child child) {
        ChildDTO childDTO = choresClient.externalResolve(child.childId);
        child = childDTO.convertToChild();
        child.listOfChores.add(chore);
        return child;
    }

     */
}
