package com.chores.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "parent")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Parent {
    @Id
    @Generated
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_uuid")
    private UUID parentUuid;

    @Column(name = "parent_name")
    private String parentName;

    @OneToMany
    @JoinColumn(name = "child_id")
    private List<Child> children;
}
