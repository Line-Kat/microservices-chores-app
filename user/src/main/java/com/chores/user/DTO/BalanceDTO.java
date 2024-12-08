package com.chores.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BalanceDTO {
    private UUID balanceUuid;
    private UUID childUuid;
    private int balanceValue;
}

