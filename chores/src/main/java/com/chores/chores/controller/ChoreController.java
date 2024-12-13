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

    // Method to add a chore to the database
    @PostMapping
    public ResponseEntity<ChoreDTO> createChore(@RequestBody ChoreDTO choreDTO) {

        Chore chore = choresService.createChore(mapToChore(choreDTO));

        return new ResponseEntity<>(mapChoreDTO(chore), HttpStatus.CREATED);
    }

    // Method to retrieve a chore from the database
    @GetMapping("/{choreUuid}")
    public ResponseEntity<ChoreDTO> getChoreByUuid(@PathVariable UUID choreUuid) {
        return choresService.getChoreByUuid(choreUuid)
                .map(chore -> new ResponseEntity<>(mapChoreDTO(chore), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Method to retrieve all the chores in the database
    @GetMapping("/allchores")
    public ResponseEntity<List<ChoreDTO>> getAllChores() {
        return choresService.getChores()
                .map(chores -> new ResponseEntity<>(mapToListOfChoreDTO(chores), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mapping

    // ChoreDTO -> Chore
    private Chore mapToChore(ChoreDTO choreDTO) {
        Chore chore = new Chore();
        chore.setChoreUuid(choreDTO.getChoreUuid());
        chore.setChoreName(choreDTO.getChoreName());

        return chore;
    }

    // Chore -> ChoreDTO
    private ChoreDTO mapChoreDTO(Chore chore) {
        ChoreDTO choreDTO = new ChoreDTO();
        choreDTO.setChoreName(chore.getChoreName());
        choreDTO.setChoreUuid(chore.getChoreUuid());

        return choreDTO;
    }

    // List<Chore> -> List<ChoreDTO>
    private List<ChoreDTO> mapToListOfChoreDTO(List<Chore> listOfChore) {
        return listOfChore
                .stream()
                .map(chore -> new ChoreDTO(chore.getChoreUuid(), chore.getChoreName()))
                .toList();
    }
}
