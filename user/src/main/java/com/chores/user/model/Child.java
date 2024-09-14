package com.chores.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "child")

public class Child {
    @Id
    @Generated
    @Column(name = "child_id")
    private Long childId;

    @Column(name = "child_uuid")
    private UUID childUuid;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToMany
    @JoinColumn(name = "childChoreId")
    private List<ChildChore> listOfChores;
}
