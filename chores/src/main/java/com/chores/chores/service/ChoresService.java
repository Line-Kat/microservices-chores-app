package com.chores.chores.service;

import com.chores.chores.model.Chore;

import java.util.List;
import java.util.Optional;

public interface ChoresService {
    Chore createChore(Chore chore);
    List<Chore> getChores();
    Optional<Chore> getChoreById(Long id);
    //Child addChoreToChild(Chore chore, Child child);
}
