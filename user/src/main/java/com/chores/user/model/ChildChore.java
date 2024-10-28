package com.chores.user.model;

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

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @Column(name = "chore_uuid")
    private UUID choreUuid;

    @Column(name = "child_chore_date")
    private Date date;

    @Column(name = "child_chore_status")
    private ChildChoreStatus status;

}
