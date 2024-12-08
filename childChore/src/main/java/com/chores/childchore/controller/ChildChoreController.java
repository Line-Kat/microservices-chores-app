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
import java.time.format.DateTimeFormatter;
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

    @DeleteMapping("/remove")
    public void deleteChildChore(@RequestBody ChildChoreDTO childChoreDTO) {
        childChoreService.deleteChildChore(mapChore(childChoreDTO));
    }

    // returns the chores of the day
    @GetMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDateDTO> getAllChoresByChildUuid(@PathVariable UUID childUuid) {
        List<ChildChoreDTO> listOfChildChoreDTO = mapListOfChildChoreDTO(childChoreService.getAllChores(childUuid));

        // return an object ChildChoreDate, so the frontend can give a positive feedback to child when all the chores
        // of the day is completed

        //mapping if childchoredto == date.now -> pakke i en ChildChoreDate (containts the date and

        return ResponseEntity.status(HttpStatus.OK).body(mapToChildChoreDateDTO(listOfChildChoreDTO));
    }

    // Mapping
    private ChildChoreDateDTO mapToChildChoreDateDTO(List<ChildChoreDTO> listOfChildChoreDTO) {
        List<ChildChoreDTO> listOfTodaysChores = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for(ChildChoreDTO childChoreDTO : listOfChildChoreDTO) {

            if(formatter.format(childChoreDTO.getDate()).equals(LocalDate.now().toString())) {
                listOfTodaysChores.add(childChoreDTO);
            }
        }

        ChildChoreDateDTO childChoreDateDTO = new ChildChoreDateDTO();
        childChoreDateDTO.setDate(listOfTodaysChores.get(0).getDate());
        childChoreDateDTO.setListOfChildChoreDTO(listOfTodaysChores);

        return childChoreDateDTO;
    }

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
