package com.chores.reward.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "balance")

@NamedQuery(name = "Balance.findBalanceByChildUuid", query = "select c from Balance c where c.childUuid = ?1")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long balanceId;

    @Column(name = "balance_uuid")
    private UUID balanceUuid;

    @Column(name = "child_uuid")
    private UUID childUuid;

    @Column(name = "balance_value")
    private int balanceValue;

}
