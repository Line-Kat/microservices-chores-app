package com.chores.chores.service;

import com.chores.chores.model.Child;
import com.chores.chores.model.Chore;

public interface ChoresService {
    Chore createChore(Chore chore);

    Child addChoreToChild(Chore chore);
}
