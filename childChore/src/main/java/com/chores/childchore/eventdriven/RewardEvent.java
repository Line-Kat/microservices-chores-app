package com.chores.childchore.eventdriven;

import lombok.Value;

import java.util.List;
import java.util.UUID;

// event that is sent to rabbitMQ
@Value()
public class RewardEvent {
    UUID childUuid;
    List<Integer> childChoreValues;
}
