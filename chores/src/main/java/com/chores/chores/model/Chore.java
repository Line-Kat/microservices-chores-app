package com.chores.chores.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "chore")
@NamedQuery(name = "Chore.findChoreByUuid", query = "select c from Chore c where c.choreUuid = ?1")
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chore_id")
    private Long choreId;

    @Column(name = "chore_uuid")
    private UUID choreUuid;

    @Column(name = "chore_name")
    private String choreName;
}
