package com.chores.childchore.controller;

import com.chores.childchore.DTO.ChildChoreDTO;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.service.ChildChoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/childchore")
@RequiredArgsConstructor
public class ChildChoreController {

    private final ChildChoreService childChoreService;

    @PostMapping("/addchore")
    public ResponseEntity<ChildChoreDTO> addChoreToChild(@RequestBody ChildChoreDTO childChoreDTO) {

        ChildChore tempChildChore = childChoreService.addChoreToChild(childChoreDTO.getChildUuid(), childChoreDTO.getChoreUuid(), childChoreDTO.getChildChoreUuid(), childChoreDTO.getDate(), childChoreDTO.getStatus());
        ChildChoreDTO tempChildChoreDTO = mapChoreDTO(tempChildChore);

        return ResponseEntity.status(HttpStatus.OK).body(tempChildChoreDTO);
    }

    @PutMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDTO> updateChildChore(@PathVariable UUID childUuid, @RequestBody ChildChoreDTO childChoreDTO) {
        ChildChore tempChildChore = childChoreService.updateChildChore(mapChildChore(childChoreDTO, childUuid));

        ChildChoreDTO newChildChoreDTO = mapChildChoreDTO(tempChildChore);

        return ResponseEntity.status(HttpStatus.OK).body(newChildChoreDTO);
    }

    // Mapping
    private ChildChore mapChildChore(ChildChoreDTO childChoreDTO, UUID childUuid) {

        ChildChore childChore = new ChildChore();

        childChore.setChildChoreUuid(childChoreDTO.getChildChoreUuid());
        childChore.setChildUuid(childUuid);
        childChore.setChoreUuid(childChoreDTO.getChoreUuid());
        childChore.setDate(childChoreDTO.getDate());
        childChore.setStatus(childChoreDTO.getStatus());

        return childChore;
    }

    private ChildChoreDTO mapChildChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus());
    }

    private ChildChoreDTO mapChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus());
    }
}
