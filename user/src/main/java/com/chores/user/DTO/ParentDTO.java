package com.chores.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ParentDTO {
    private UUID parentUuid;
    private String parentName;
    private List<ChildDTO> children;
}
