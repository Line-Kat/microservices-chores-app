package com.chores.childchore.DTO;

import com.chores.childchore.model.ChildChoreStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildChoreDTO {
    private UUID childChoreUuid;
    private UUID childUuid;
    private UUID choreUuid;
    private Date date;
    private ChildChoreStatus status;
    private int value;
}
