package com.chores.reward.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "saving_goal")

@NamedQuery(name = "SavingGoal.findSavingGoalByChildUuid", query = "select s from SavingGoal s where s.childUuid = ?1")
public class SavingGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saving_goal_id")
    private Long savingGoalId;

    @Column(name = "saving_goal_uuid")
    private UUID savingGoalUuid;

    @Column(name = "child_uuid")
    private UUID childUuid;

    @Column(name = "saving_goal_name")
    private String savingGoalName;

    @Column(name = "saving_goal_value")
    private Long savingGoalValue;
}
