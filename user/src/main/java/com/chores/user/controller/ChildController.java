package com.chores.user.controller;

import com.chores.user.DTO.ChildChoreDTO;
import com.chores.user.DTO.ChildDTO;
import com.chores.user.model.Child;
import com.chores.user.model.ChildChore;
import com.chores.user.service.ChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
@Slf4j
public class ChildController {

    private final ChildService childService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ChildDTO> findChildByUuid(@PathVariable UUID uuid) {
        return childService.findChildByUuid(uuid)
                .map(child -> new ResponseEntity<>(mapChildDTO(child), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<ChildDTO> createChild(@RequestBody ChildDTO childDTO) {

        Child newChild = childService.createChild(mapChild(childDTO), childDTO.getParentId());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ChildDTO(newChild.getChildUuid(), newChild.getChildName(), newChild.getParent().getParentUuid(), null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildDTO> addChoreToChild(@PathVariable Long id, @RequestBody ChildDTO childDTO) {
        Child newChild = childService.addChoreToChild(mapChild(childDTO), id);
        return ResponseEntity.status(HttpStatus.OK).body(mapChildDTO(newChild));

    }

    private Child mapChild(ChildDTO childDTO) {
        Child newChild = new Child();
        ChildChore newChildChore = new ChildChore();
        newChild.setChildUuid(childDTO.getChildUuid());
        newChild.setChildName(childDTO.getChildName());
        newChild.setListOfChores(childDTO.getListOfChores().stream().map(childChoreDTO -> new ChildChore(newChildChore.getChildChoreId(), newChildChore.getChildChoreUuid(), newChildChore.getChild(), newChildChore.getChoreId())).toList());

        return newChild;
    }

    private ChildDTO mapChildDTO(Child child) {
        return new ChildDTO(child.getChildUuid(), child.getChildName(), child.getParent().getParentUuid(), child.getListOfChores().stream().map(childChore -> new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChild().getChildUuid(), null)).toList());
    }
}
