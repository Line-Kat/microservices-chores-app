package com.chores.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Chore {
    @Id
    public Long choreId;

    public String choreName;
}
