package com.chores.chores.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChoreDTO {
    private UUID choreUuid;
    private String choreName;
}
