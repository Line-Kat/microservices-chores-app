package com.chores.user.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SavingGoal {
    private UUID savingGoalUuid;
    private UUID childUuid;
    private String savingGoalName;
    private Long savingGoalValue;
}
