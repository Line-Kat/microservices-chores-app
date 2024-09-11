package com.chores.chores.controller;

import com.chores.chores.model.Child;
import com.chores.chores.model.Chore;
import com.chores.chores.service.ChoresServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chore")
@RequiredArgsConstructor
public class ChoreController {
    private final ChoresServiceImpl choreService;

    @PostMapping
    public Chore createChore(@RequestBody Chore chore) {
        return choreService.createChore(chore);
    }

    // this should be a put!
    @PostMapping("/child")
    public Child addChoreToChild(@RequestBody Chore chore, Child child) {
        return choreService.addChoreToChild(chore, child);
    }
}
