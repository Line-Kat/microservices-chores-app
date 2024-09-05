package com.chores.user.controller;

import com.chores.user.model.Parent;
import com.chores.user.service.ParentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/parent/{id}")
    public Parent getParentById(@PathVariable Long id) {
        return parentService.getParentById(id);
    }

    @PostMapping("/parent")
    public Parent createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

}
