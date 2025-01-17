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

@NamedQuery(name = "Parent.findByParentUuid", query = "select p from Parent p where p.parentUuid = ?1")

public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_uuid")
    private UUID parentUuid;

    @Column(name = "parent_name")
    private String parentName;

    @OneToMany
    private List<Child> children;
}
