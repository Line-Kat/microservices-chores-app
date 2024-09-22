package com.chores.chores.controller;

import com.chores.chores.model.Chore;
import com.chores.chores.service.ChoresServiceImpl;
import com.chores.user.DTO.ChoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chore")
@RequiredArgsConstructor
public class ChoreController {
    private final ChoresServiceImpl choreService;

    @PostMapping
    public Chore createChore(@RequestBody Chore chore) {
        return choreService.createChore(chore);
    }

    @GetMapping("/allchores")
    public List<Chore> getAllChores() {
        return choreService.getChores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChoreDTO> getChoreById(@PathVariable Long id) {
        return choreService.getChoreById(id)
                .map(chore -> new ResponseEntity<>(mapChoreDTO(chore), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        /**
         * return childService.findChildByUuid(uuid)
         *                 .map(child -> new ResponseEntity<>(mapChildDTO(child), HttpStatus.OK))
         *                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
         */

    }

    private ChoreDTO mapChoreDTO(Chore chore) {
        ChoreDTO choreDTO = new ChoreDTO();
        choreDTO.setChoreId(chore.getChoreId());
        choreDTO.setChoreName(chore.getChoreName());
        return choreDTO;
    }
    /*
    // this should be a put!
    @PostMapping("/child")
    public Child addChoreToChild(@RequestBody Chore chore, Child child) {
        return choreService.addChoreToChild(chore, child);
    }

     */
}
