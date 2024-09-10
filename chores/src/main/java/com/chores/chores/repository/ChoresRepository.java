package com.chores.chores.repository;

import com.chores.chores.model.Chore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoresRepository extends JpaRepository<Chore, Long> {
}
