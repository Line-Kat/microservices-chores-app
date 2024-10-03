package com.chores.chores.controller;

import com.chores.chores.DTO.ChoreDTO;
import com.chores.chores.model.Chore;
import com.chores.chores.service.ChoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chore")
@RequiredArgsConstructor
public class ChoreController {
    private final ChoresService choresService;

    @PostMapping
    public ResponseEntity<ChoreDTO> createChore(@RequestBody ChoreDTO choreDTO) {
        Chore newestChore = new Chore();
        newestChore.setChoreUuid(choreDTO.getChoreUuid());
        newestChore.setChoreName(choreDTO.getChoreName());

        Chore newChore = choresService.createChore(newestChore);

        return new ResponseEntity<>(mapChoreDTO(newChore), HttpStatus.CREATED);
    }

    @GetMapping("/allchores")
    public List<Chore> getAllChores() {
        return choresService.getChores();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ChoreDTO> getChoreByUuid(@PathVariable UUID uuid) {
        return choresService.getChoreByUuid(uuid)
                .map(chore -> new ResponseEntity<>(mapChoreDTO(chore), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private ChoreDTO mapChoreDTO(Chore chore) {
        ChoreDTO choreDTO = new ChoreDTO();
        choreDTO.setChoreName(chore.getChoreName());
        choreDTO.setChoreUuid(chore.getChoreUuid());
        return choreDTO;
    }
}
