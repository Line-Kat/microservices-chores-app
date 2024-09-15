package com.chores.user.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChoreDTO {
    private Long choreId;
    private UUID choreUuid;
    private String choreName;
}
