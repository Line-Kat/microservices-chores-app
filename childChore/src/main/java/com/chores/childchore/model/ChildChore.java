package com.chores.childchore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "child_chore")

@NamedQuery(name = "ChildChore.findChildChoreByUuid", query = "select c from ChildChore c where c.childChoreUuid = ?1")
public class ChildChore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_chore_id")
    private Long childChoreId;

    @Column(name = "child_chore_uuid")
    private UUID childChoreUuid;

    @JoinColumn(name = "child_uuid")
    private UUID childUuid;

    @Column(name = "chore_uuid")
    private UUID choreUuid;

    @Column(name = "child_chore_date")
    private Date date;

    @Column(name = "child_chore_status")
    private ChildChoreStatus status;

    @Column(name = "child_chore_value")
    private int value;
}
