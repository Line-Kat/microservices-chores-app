package com.chores.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingGoalDTO {
    private UUID savingGoalUuid;
    private UUID childUuid;
    private String savingGoalName;
    private Long savingGoalValue;
}
