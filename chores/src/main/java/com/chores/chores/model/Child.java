package com.chores.chores.model;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Child {
    public Long childId;
    public String childName;
    public List<Chore> listOfChores = new ArrayList<>();
}
