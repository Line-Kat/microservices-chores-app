package com.chores.childchore.eventdriven;

import com.chores.childchore.model.ChildChore;
import lombok.Value;

import java.util.List;

// event that is sent to rabbitMQ
@Value()
public class RewardEvent {
    List<ChildChore> listOfCompletedChores;
}
