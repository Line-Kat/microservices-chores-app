package com.chores.user.controller;

import com.chores.user.model.Child;
import com.chores.user.service.ChildService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/parent/{id}")
    public Child getChildById(long id) {
        return childService.getChildById(id);
    }
}
