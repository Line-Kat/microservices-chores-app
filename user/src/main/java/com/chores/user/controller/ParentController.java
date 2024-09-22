package com.chores.user.controller;

import com.chores.user.model.Parent;
import com.chores.user.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
@Slf4j
public class ParentController {

    private final ParentService parentService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Parent> findParentByUuid(@PathVariable UUID uuid) {
        return parentService.findParentByUuid(uuid)
                .map(parent -> new ResponseEntity<>(parent, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

}
