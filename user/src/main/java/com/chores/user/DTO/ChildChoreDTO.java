package com.chores.user.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChildChoreDTO {
    private UUID childChoreUuid;
    private UUID childUuid;
    private ChoreDTO chore;
}
