package com.chores.user.eventdriven;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value()
public class RewardEvent {
    UUID childUuid;
    List<UUID> choreUuids;
}
