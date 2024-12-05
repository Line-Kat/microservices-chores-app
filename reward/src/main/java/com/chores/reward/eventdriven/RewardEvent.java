package com.chores.reward.eventdriven;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class RewardEvent {
    UUID childUuid;
    List<Integer> childChoreValues;
}
