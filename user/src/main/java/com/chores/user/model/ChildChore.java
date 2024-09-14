package com.chores.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildChore {
    @Id
    private Long childChoreId;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @Column(name = "chore_id")
    private Long choreId;

}
