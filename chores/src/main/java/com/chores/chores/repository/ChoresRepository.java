package com.chores.chores.repository;

import com.chores.chores.model.Chore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChoresRepository extends JpaRepository<Chore, Long> {
    Optional<Chore> findChoreByUuid(UUID uuid);
}
