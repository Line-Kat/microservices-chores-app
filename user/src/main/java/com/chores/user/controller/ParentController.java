package com.chores.user.controller;

import com.chores.user.DTO.ChildDTO;
import com.chores.user.DTO.ParentDTO;
import com.chores.user.model.Child;
import com.chores.user.model.Parent;
import com.chores.user.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
@Slf4j
public class ParentController {

    private final ParentService parentService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ParentDTO> findParentByUuid(@PathVariable UUID uuid) {
        return parentService.findParentByUuid(uuid)
                .map(parent -> new ResponseEntity<>(mapParentDTO(parent), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

    // Mapping
    private ParentDTO mapParentDTO(Parent parent) {
        return new ParentDTO(parent.getParentUuid(), parent.getParentName(), mapListOfChildDTO(parent.getChildren()));
    }

    private List<ChildDTO> mapListOfChildDTO(List<Child> listOfChild) {
        return listOfChild
                .stream()
                .map(child -> new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid()))
                .toList();
    }
}
