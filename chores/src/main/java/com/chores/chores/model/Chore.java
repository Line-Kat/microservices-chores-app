package com.chores.chores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "chore")
public class Chore {
    @Id
    @Generated
    @Column(name = "chore_id")
    private Long choreId;

    @Column(name = "chore_name")
    private String choreName;
}
