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
public class ChildChoreDTO {
    private UUID childChoreUuid;
    private UUID childUuid;
    private ChoreDTO chore;
}
