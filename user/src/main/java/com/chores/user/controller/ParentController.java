package com.chores.user.controller;

import com.chores.user.DTO.ChildDTO;
import com.chores.user.DTO.ParentDTO;
import com.chores.user.model.Child;
import com.chores.user.model.Parent;
import com.chores.user.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    // Method to create a parent
    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

    // Method to find a parent
    @GetMapping("/{parentUuid}")
    public ResponseEntity<ParentDTO> findParentByUuid(@PathVariable UUID parentUuid) {
        return parentService.findParentByUuid(parentUuid)
                .map(parent -> new ResponseEntity<>(mapToParentDTO(parent), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mapping

    // Parent -> ParentDTO
    private ParentDTO mapToParentDTO(Parent parent) {
        return new ParentDTO(parent.getParentUuid(), parent.getParentName(), mapToListOfChildDTO(parent.getChildren()));
    }

    // List<Child> -> List<ChildDTO>
    private List<ChildDTO> mapToListOfChildDTO(List<Child> listOfChild) {
        return listOfChild
                .stream()
                .map(child -> new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid()))
                .toList();
    }
}
