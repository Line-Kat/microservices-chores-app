package com.chores.user.repository;

import com.chores.user.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findChildByUuid(UUID uuid);
}
