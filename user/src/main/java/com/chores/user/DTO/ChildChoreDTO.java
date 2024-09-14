package com.chores.user.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChildChoreDTO {
    private UUID childChoreId;
    private UUID childId;
    private ChoreDTO chore;
}
