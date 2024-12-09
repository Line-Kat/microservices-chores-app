package com.chores.childchore.eventdriven;

import com.chores.childchore.DTO.ChildChoreDateDTO;
import lombok.Value;

// Event that is sent to rabbitMQ
@Value()
public class RewardEvent {
    ChildChoreDateDTO childChoreDateDTO;
}
