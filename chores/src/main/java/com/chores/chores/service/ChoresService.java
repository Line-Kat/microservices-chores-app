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

    public Chore createChore(Chore chore) {
        return choresRepository.save(chore);
    }

    public List<Chore> getChores() {
        return choresRepository.findAll();
    }

    public Optional<Chore> getChoreByUuid(UUID uuid) {
        return choresRepository.findChoreByUuid(uuid);
    }
}
