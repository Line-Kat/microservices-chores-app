package com.chores.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    private List<Chore> listOfChores;

    //foreign key to the parent the child belongs to
}
