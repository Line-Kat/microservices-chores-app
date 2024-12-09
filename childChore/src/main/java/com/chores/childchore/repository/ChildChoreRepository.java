package com.chores.childchore.repository;

import com.chores.childchore.model.ChildChore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildChoreRepository extends JpaRepository<ChildChore, Long> {
    Optional<ChildChore> findChildChoreByUuid(UUID uuid);
    List<ChildChore> findAllChoresByChildUuid(UUID childUuid);
}
