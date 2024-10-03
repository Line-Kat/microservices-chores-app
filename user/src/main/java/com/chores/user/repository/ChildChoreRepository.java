package com.chores.user.repository;

import com.chores.user.model.ChildChore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildChoreRepository extends JpaRepository<ChildChore, Long> {

}
