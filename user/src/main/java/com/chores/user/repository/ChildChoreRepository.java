package com.chores.user.repository;

import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildChoreRepository extends JpaRepository<ChildChore, Long> {
    Optional<ChildChore> findChildChoreByUuid(UUID uuid);
}
