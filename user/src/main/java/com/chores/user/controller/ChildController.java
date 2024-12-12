package com.chores.user.controller;

import com.chores.user.DTO.BalanceDTO;
import com.chores.user.DTO.ChildDTO;
import com.chores.user.DTO.SavingGoalDTO;
import com.chores.user.model.Child;
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

    // Method to create a child
    @PostMapping
    public ResponseEntity<ChildDTO> createChild(@RequestBody ChildDTO childDTO) {

        Child newChild = childService.createChild(mapToChild(childDTO), childDTO.getParentUuid());

        return ResponseEntity.status(HttpStatus.CREATED).body(mapToChildDTO(newChild));
    }




    @GetMapping("/{uuid}")
    public ResponseEntity<ChildDTO> findChildByUuid(@PathVariable UUID uuid) {
        return childService.findChildByUuid(uuid)
                .map(child -> new ResponseEntity<>(mapToChildDTO(child), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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


    @PutMapping("/balance/update")
    public ResponseEntity<BalanceDTO> updateBalance(@RequestBody BalanceDTO balanceDTO) {

        BalanceDTO newBalanceDTO = childService.parentUpdateBalance(balanceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newBalanceDTO);
    }

    // Call from Postman/frontend
    @PostMapping("/goal")
    public ResponseEntity<SavingGoalDTO> createSavingGoal(@RequestBody SavingGoalDTO savingGoalDTO) {

        SavingGoalDTO newSavingGoalDTO = childService.createSavingGoal(savingGoalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newSavingGoalDTO);
    }

    // Mapping

    // Child -> ChildDTO
    private ChildDTO mapToChildDTO(Child child) {
        return new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid());
    }

    // ChildDTO -> Child
    private Child mapToChild(ChildDTO childDTO) {
        Child newChild = new Child();
        newChild.setChildUuid(childDTO.getChildUuid());
        newChild.setChildName(childDTO.getChildName());

        return newChild;
    }


}
