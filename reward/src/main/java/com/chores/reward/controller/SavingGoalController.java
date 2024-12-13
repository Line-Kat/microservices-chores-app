package com.chores.reward.controller;

import com.chores.reward.DTO.SavingGoalDTO;
import com.chores.reward.model.SavingGoal;
import com.chores.reward.service.SavingGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reward/goal")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    // Method to create a child's savingGoal
    @PostMapping
    public ResponseEntity<SavingGoalDTO> createSavingGoal(@RequestBody SavingGoalDTO savingGoalDTO) {
        SavingGoal savingGoal = savingGoalService.createSavingGoal(mapSavingGoal(savingGoalDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapSavingGoalDTO(savingGoal));
    }

    // Method to get a child's saving goal
    @GetMapping("/{childUuid}")
    public ResponseEntity<SavingGoalDTO> getSavingGoal(@PathVariable UUID childUuid) {
        return savingGoalService.getSavingGoal(childUuid)
                .map(savingGoal -> new ResponseEntity<>(mapSavingGoalDTO(savingGoal), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mapping

    // SavingGoal -> SavingGoalDTO
    private SavingGoalDTO mapSavingGoalDTO(SavingGoal savingGoal) {
        return new SavingGoalDTO(savingGoal.getSavingGoalUuid(), savingGoal.getChildUuid(), savingGoal.getSavingGoalName(), savingGoal.getSavingGoalValue());
    }

    // SavingGoalDTO -> SavingGoal
    private SavingGoal mapSavingGoal(SavingGoalDTO savingGoalDTO) {
            SavingGoal savingGoal = new SavingGoal();
            savingGoal.setSavingGoalUuid(savingGoalDTO.getSavingGoalUuid());
            savingGoal.setChildUuid(savingGoalDTO.getChildUuid());
            savingGoal.setSavingGoalName(savingGoalDTO.getSavingGoalName());
            savingGoal.setSavingGoalValue(savingGoalDTO.getSavingGoalValue());
        return savingGoal;
    }
}
