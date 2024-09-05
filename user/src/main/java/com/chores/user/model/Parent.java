package com.chores.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table (name = "parent")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Parent {
    @Id
    @Generated
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_name")
    private String parentName;
}
