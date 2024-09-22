package com.chores.user.DTO;

import com.chores.user.model.ChildChore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChoreDTO {
    private Long choreId;
    private UUID choreUuid;
    private String choreName;

    public ChildChore convertToChild() {
        ChildChore childChore = new ChildChore();
        childChore.setChildChoreId(this.choreId);
        childChore.setChildChoreUuid(this.choreUuid);
        //childChore.setChild();
        childChore.setChoreId(this.choreId);
        return childChore;
    }
}
