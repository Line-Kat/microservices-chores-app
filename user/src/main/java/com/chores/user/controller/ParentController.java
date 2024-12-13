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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    // Method to create a parent
    @PostMapping
    public ResponseEntity<ParentDTO> createParent(@RequestBody ParentDTO parentDTO) {

        Parent parent = parentService.createParent(mapToParent(parentDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapToParentDTO(parent));
    }

    // Method to find a parent
    @GetMapping("/{parentUuid}")
    public ResponseEntity<ParentDTO> findParentByUuid(@PathVariable UUID parentUuid) {
        return parentService.findParentByUuid(parentUuid)
                .map(parent -> new ResponseEntity<>(mapToParentDTO(parent), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mapping

    // Parent -> ParentDTO
    private ParentDTO mapToParentDTO(Parent parent) {
        return new ParentDTO(parent.getParentUuid(), parent.getParentName(), mapToListOfChildDTO(parent.getChildren()));
    }

    // ParentDTO -> Parent
    private Parent mapToParent(ParentDTO parentDTO) {
        Parent parent = new Parent();
        parent.setParentUuid(parentDTO.getParentUuid());
        parent.setParentName(parentDTO.getParentName());
        parent.setChildren(mapToListOfChild(parentDTO.getChildren()));

        return parent;
    }

    // List<Child> -> List<ChildDTO>
    private List<ChildDTO> mapToListOfChildDTO(List<Child> listOfChild) {
        return listOfChild
                .stream()
                .map(child -> new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid()))
                .toList();
    }

    // List<ChildDTO> -> List<Child>
    private List<Child> mapToListOfChild(List<ChildDTO> listOfChildDTO) {
        // When creating a parent, the list of children is null
        if(listOfChildDTO == null) {
            return Collections.emptyList();
        }

        return listOfChildDTO
                .stream()
                .map(childDTO -> {
                    Child child = new Child();
                    child.setChildUuid(childDTO.getChildUuid());
                    child.setChildName(childDTO.getChildName());
                    child.setParent(parentService.findParentByUuid(childDTO.getParentUuid()).orElseThrow());
                    return child;
                })
                .toList();
    }
}
