package com.chores.chores.service;

import com.chores.chores.model.Chore;
import com.chores.chores.repository.ChoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ChoresService {
    private final ChoresRepository choresRepository;

    // Method to add a chore to the database
    public Chore createChore(Chore chore) {
        return choresRepository.save(chore);
    }

    // Method to retrieve a chore from the database
    public Optional<Chore> getChoreByUuid(UUID uuid) {
        return choresRepository.findChoreByUuid(uuid);
    }

    // Method to get all chores
    public Optional<List<Chore>> getChores() {
        return choresRepository.findAllChores();
    }
}
