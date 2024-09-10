package com.chores.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "child")

public class Child {
    @Id
    @Generated
    @Column(name = "child_id")
    private Long childId;

    @Column(name = "child_name")
    private String childName;
}
