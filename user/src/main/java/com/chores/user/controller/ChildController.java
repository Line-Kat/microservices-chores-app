package com.chores.user.controller;

import com.chores.user.DTO.ChildDTO;
import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
import com.chores.user.service.ChildService;
import com.chores.user.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
@Slf4j
public class ChildController {

    private final ChildService childService;
    //private final ParentService parentService;

    @GetMapping("/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable Long id) {
        return childService.getChildById(id)
                .map(child -> new ResponseEntity<>(child, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChildDTO> createChild(@RequestBody ChildDTO childDTO) {

        Child newChild = childService.createChild(mapChild(childDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ChildDTO(newChild.getChildUuid(), newChild.getChildName(), newChild.getParent().getParentUuid(), null));
    }

    private Child mapChild(ChildDTO childDTO) {
        Child newChild = new Child();
        newChild.setChildUuid(childDTO.getChildUuid());
        newChild.setChildName(childDTO.getChildName());
        newChild.setListOfChores(childDTO.getListOfChores().stream().map(childChoreDTO -> new ChildChore(null, null, null)).toList());
        return newChild;
    }

}
