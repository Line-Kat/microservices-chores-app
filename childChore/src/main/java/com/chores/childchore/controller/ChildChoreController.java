package com.chores.childchore.controller;

import com.chores.childchore.DTO.ChildChoreDTO;
import com.chores.childchore.DTO.ChildChoreDateDTO;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.service.ChildChoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/childchore")
@RequiredArgsConstructor
public class ChildChoreController {

    private final ChildChoreService childChoreService;

    // Method to add a chore to a child
    @PostMapping("/addchore")
    public ResponseEntity<ChildChoreDTO> addChoreToChild(@RequestBody ChildChoreDTO childChoreDTO) {

        ChildChore childChore = childChoreService.addChoreToChild(mapToChildChore(childChoreDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapToChildChoreDTO(childChore));
    }

    // Method to return a child's list of today's chores
    @GetMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDateDTO> getChoresOfToday(@PathVariable UUID childUuid) {
        List<ChildChoreDTO> listOfChildChoreDTO = mapToListOfChildChoreDTO(childChoreService.getAllChores(childUuid));

        return ResponseEntity.status(HttpStatus.OK).body(mapToChildChoreDateDTO(listOfChildChoreDTO));
    }







    @PutMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDTO> updateChildChore(@PathVariable UUID childUuid, @RequestBody ChildChoreDTO childChoreDTO) {
        ChildChore tempChildChore = childChoreService.updateChildChore(mapChildChore(childChoreDTO, childUuid));

        ChildChoreDTO newChildChoreDTO = mapToChildChoreDTO(tempChildChore);

        return ResponseEntity.status(HttpStatus.OK).body(newChildChoreDTO);
    }

    @DeleteMapping("/remove")
    public void deleteChildChore(@RequestBody ChildChoreDTO childChoreDTO) {
        childChoreService.deleteChildChore(mapToChildChore(childChoreDTO));
    }



    // Mapping
    private ChildChoreDateDTO mapToChildChoreDateDTO(List<ChildChoreDTO> listOfChildChoreDTO) {
        List<ChildChoreDTO> listChoresOfToday = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for(ChildChoreDTO childChoreDTO : listOfChildChoreDTO) {
            if(formatter.format(childChoreDTO.getDate()).equals(LocalDate.now().toString())) {
                listChoresOfToday.add(childChoreDTO);
            }
        }

        return new ChildChoreDateDTO((LocalDate.now()), listChoresOfToday);
    }

    private ChildChoreDTO mapToChildChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus(), childChore.getValue());
    }

    private ChildChore mapToChildChore(ChildChoreDTO childChoreDTO) {
        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreDTO.getChildChoreUuid());
        childChore.setChildUuid(childChoreDTO.getChildUuid());
        childChore.setChoreUuid(childChoreDTO.getChoreUuid());
        childChore.setDate(childChoreDTO.getDate());
        childChore.setStatus(childChoreDTO.getStatus());
        childChoreDTO.setValue(childChoreDTO.getValue());

        return childChore;
    }

    private List<ChildChoreDTO> mapToListOfChildChoreDTO(List<ChildChore> listOfChildChore) {
        List<ChildChoreDTO> listOfChildChoreDTO = new ArrayList<>();
        for(ChildChore cc : listOfChildChore) {
            listOfChildChoreDTO.add(mapToChildChoreDTO(cc));
        }

        return listOfChildChoreDTO;
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



}
