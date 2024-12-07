package com.chores.user.controller;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.ChildDTO;
import com.chores.user.DTO.SavingGoalDTO;
import com.chores.user.model.Balance;
import com.chores.user.model.Child;
import com.chores.user.model.SavingGoal;
import com.chores.user.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ChildDTO> findChildByUuid(@PathVariable UUID uuid) {
        return childService.findChildByUuid(uuid)
                .map(child -> new ResponseEntity<>(mapChildDTO(child), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChildDTO> createChild(@RequestBody ChildDTO childDTO) {

        Child newChild = childService.createChild(mapChild(childDTO), childDTO.getParentId());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ChildDTO(newChild.getChildUuid(), newChild.getChildName(), newChild.getParent().getParentUuid()));
    }

    @GetMapping("/balance/{childUuid}")
    public ResponseEntity<BalanceDTO> getBalance(@PathVariable UUID childUuid) {
        return childService.getBalance(childUuid)
                .map(balanceDTO -> new ResponseEntity<>(balanceDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/goal/{childUuid}")
    public ResponseEntity<SavingGoalDTO> getSavingGoal(@PathVariable UUID childUuid) {
        return childService.getSavingGoal(childUuid)
                .map(savingGoalDTO -> new ResponseEntity<>(savingGoalDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/balance/update/{childUuid}")
    public ResponseEntity<BalanceDTO> updateBalance(@PathVariable UUID childUuid, @RequestBody BalanceDTO balanceDTO) {
        BalanceDTO newBalanceDTO = childService.parentUpdateBalance(childUuid, balanceDTO);

        return ResponseEntity.status(HttpStatus.OK).body(newBalanceDTO);
    }

    // Call from Postman/frontend
    @PostMapping("/goal")
    public ResponseEntity<SavingGoalDTO> createSavingGoal(@RequestBody SavingGoalDTO savingGoalDTO) {

        SavingGoalDTO newSavingGoalDTO = childService.createSavingGoal(savingGoalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newSavingGoalDTO);
    }


    private ChildDTO mapChildDTO(Child child) {
        return new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid());
    }

    private Child mapChild(ChildDTO childDTO) {
        Child newChild = new Child();
        newChild.setChildUuid(childDTO.getChildUuid());
        newChild.setChildName(childDTO.getChildName());

        return newChild;
    }


}
