package com.chores.childchore.controller;

import com.chores.childchore.DTO.ChildChoreDTO;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.service.ChildChoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/childchore")
@RequiredArgsConstructor
public class ChildChoreController {

    private final ChildChoreService childChoreService;

    @PostMapping("/addchore")
    public ResponseEntity<ChildChoreDTO> addChoreToChild(@RequestBody ChildChoreDTO childChoreDTO) {

        ChildChore tempChildChore = childChoreService.addChoreToChild(childChoreDTO.getChildUuid(), childChoreDTO.getChoreUuid(), childChoreDTO.getChildChoreUuid(), childChoreDTO.getDate(), childChoreDTO.getStatus(), childChoreDTO.getValue());
        ChildChoreDTO tempChildChoreDTO = mapChoreDTO(tempChildChore);

        return ResponseEntity.status(HttpStatus.OK).body(tempChildChoreDTO);
    }

    @PutMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDTO> updateChildChore(@PathVariable UUID childUuid, @RequestBody ChildChoreDTO childChoreDTO) {
        ChildChore tempChildChore = childChoreService.updateChildChore(mapChildChore(childChoreDTO, childUuid));

        ChildChoreDTO newChildChoreDTO = mapChildChoreDTO(tempChildChore);

        return ResponseEntity.status(HttpStatus.OK).body(newChildChoreDTO);
    }

    @GetMapping("/{childUuid}")
    public ResponseEntity<List<ChildChoreDTO>> getAllChoresByChildUuid(@PathVariable UUID childUuid) {
        List<ChildChoreDTO> listOfChildChoreDTO = mapListOfChildChoreDTO(childChoreService.getAllChores(childUuid));
        return ResponseEntity.status(HttpStatus.OK).body(listOfChildChoreDTO);
    }

    @DeleteMapping("/remove")
    public void deleteChildChore(@RequestBody ChildChoreDTO childChoreDTO) {
        childChoreService.deleteChildChore(mapChore(childChoreDTO));
    }

    // Mapping
    private List<ChildChoreDTO> mapListOfChildChoreDTO(List<ChildChore> listOfChildChore) {
        List<ChildChoreDTO> listOfChildChoreDTO = new ArrayList<>();
        for(ChildChore cc : listOfChildChore) {
            listOfChildChoreDTO.add(mapChoreDTO(cc));
        }

        return listOfChildChoreDTO;
    }

    private ChildChore mapChore(ChildChoreDTO childChoreDTO) {
        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreDTO.getChildChoreUuid());
        childChore.setChildUuid(childChore.getChildUuid());
        childChore.setChoreUuid(childChoreDTO.getChoreUuid());
        childChore.setDate(childChoreDTO.getDate());
        childChore.setStatus(childChoreDTO.getStatus());

        return childChore;
    }

    // Refactor
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
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus(), childChore.getValue());
    }

    private ChildChoreDTO mapChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus(), childChore.getValue());
    }
}
