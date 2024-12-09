package com.chores.reward.eventdriven;

import com.chores.reward.DTO.ChildChoreDateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RewardEvent {
    ChildChoreDateDTO childChoreDateDTO;
}
