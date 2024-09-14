package com.chores.user.DTO;

import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ChildDTO {
    private UUID childUuid;
    private String childName;
    private UUID parentId;
    private List<ChildChoreDTO> listOfChores;
}
