package com.chores.reward.controller;

import com.chores.reward.DTO.BalanceDTO;
import com.chores.reward.DTO.ChangeInBalanceDTO;
import com.chores.reward.model.Balance;
import com.chores.reward.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reward/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    // Method to create a child's balance
    @PostMapping
    public ResponseEntity<BalanceDTO> addBalanceToChild(@RequestBody BalanceDTO balanceDTO) {

        Balance balance = balanceService.addBalanceToChild(balanceDTO.getChildUuid());

        return ResponseEntity.status(HttpStatus.CREATED).body(mapBalanceDTO(balance));
    }

    // Method to get a child's balance
    @GetMapping("/{childUuid}")
    public ResponseEntity<BalanceDTO> getBalance(@PathVariable UUID childUuid) {
        return balanceService.getBalance(childUuid)
                .map(balance -> new ResponseEntity<>(mapBalanceDTO(balance), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Method for parent to update a child's chore
    @PutMapping("/update")
    public ResponseEntity<BalanceDTO> parentUpdateBalance(@RequestBody ChangeInBalanceDTO changeInBalanceDTO) {
        Balance balance = balanceService.parentUpdateBalance(changeInBalanceDTO.getChildUuid(), changeInBalanceDTO.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body(mapBalanceDTO(balance));
    }

    // Mapping

    // Balance -> BalanceDTO
    private BalanceDTO mapBalanceDTO(Balance balance) {
        return new BalanceDTO(balance.getBalanceUuid(), balance.getChildUuid(), balance.getBalanceValue());
    }

    // BalanceDTO -> Balance
    private Balance mapBalance(BalanceDTO balanceDTO) {
        Balance balance = new Balance();
        balance.setBalanceUuid(balanceDTO.getBalanceUuid());
        balance.setChildUuid(balanceDTO.getChildUuid());
        balance.setBalanceValue(balanceDTO.getBalanceValue());

        return balance;
    }
}
