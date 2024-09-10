package com.chores.chores.DTO;

import com.chores.chores.model.Child;
import com.chores.chores.model.Chore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildDTO {
    //this class can be richer than the other service
    private Long childId;
    private String childName;

    public Child convertToChild() {
        Child child = new Child();
        child.setChildId(this.childId);
        child.setChildName(this.childName);
        return child;
    }
}
