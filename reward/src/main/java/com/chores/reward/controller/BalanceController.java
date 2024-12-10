package com.chores.reward.controller;

import com.chores.reward.DTO.BalanceDTO;
import com.chores.reward.model.Balance;
import com.chores.reward.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/reward/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<BalanceDTO> createBalance(@RequestBody BalanceDTO balanceDTO) {

        Balance balance = balanceService.addBalanceToChild(balanceDTO.getChildUuid());
        BalanceDTO createdBalanceDTO = mapBalanceDTO(balance);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBalanceDTO);
    }

    @GetMapping("/{childUuid}")
    public ResponseEntity<BalanceDTO> getBalance(@PathVariable UUID childUuid) {
        return balanceService.getBalance(childUuid)
                .map(balance -> new ResponseEntity<>(mapBalanceDTO(balance), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{childUuid}")
    public ResponseEntity<BalanceDTO> parentUpdateBalance(@PathVariable UUID childUuid, @RequestBody BalanceDTO balanceDTO) {
        Balance tempBalance = balanceService.parentUpdateBalance(mapBalance(childUuid, balanceDTO));
        return ResponseEntity.status(HttpStatus.OK).body(mapBalanceDTO(tempBalance));
    }

    // Mapping
    private BalanceDTO mapBalanceDTO(Balance balance) {
        return new BalanceDTO(balance.getBalanceUuid(), balance.getChildUuid(), balance.getBalanceValue());
    }

    private Balance mapBalance(UUID childUuid, BalanceDTO balanceDTO) {
        Balance balance = new Balance();
        balance.setBalanceUuid(balanceDTO.getBalanceUuid());
        balance.setChildUuid(childUuid);
        balance.setBalanceValue(balanceDTO.getBalanceValue());

        return balance;
    }

}
