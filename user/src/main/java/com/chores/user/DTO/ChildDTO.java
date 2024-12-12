package com.chores.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ChildDTO {
    private UUID childUuid;
    private String childName;
    private UUID parentUuid;
}
