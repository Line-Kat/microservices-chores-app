package com.chores.childchore.controller;

import com.chores.childchore.DTO.ChildChoreDTO;
import com.chores.childchore.DTO.ChildChoreDateDTO;
import com.chores.childchore.model.ChildChore;
import com.chores.childchore.service.ChildChoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/childchore")
@RequiredArgsConstructor
public class ChildChoreController {

    private final ChildChoreService childChoreService;

    // Method to add a chore to a child
    @PostMapping("/addchore")
    public ResponseEntity<ChildChoreDTO> addChoreToChild(@RequestBody ChildChoreDTO childChoreDTO) {
        try{
            // Create the childChore object
            ChildChore childChore = childChoreService.addChoreToChild(mapToChildChore(childChoreDTO));

            return ResponseEntity.status(HttpStatus.CREATED).body(mapToChildChoreDTO(childChore));
        } catch (Exception e) {
            log.error("Error adding chore to child: ", e);
            // Return a BAD_REQUEST response if an exception occurs
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Method to return a child's list of today's chores
    @GetMapping("/{childUuid}")
    public ResponseEntity<ChildChoreDateDTO> getChoresOfToday(@PathVariable UUID childUuid) {
        List<ChildChoreDTO> listOfChildChoreDTO = mapToListOfChildChoreDTO(childChoreService.getAllChores(childUuid));

        return ResponseEntity.status(HttpStatus.OK).body(mapToChildChoreDateDTO(listOfChildChoreDTO));
    }

    // Method to update a child's chore (status, date or value)
    // /{field}, field can be 'status', 'date' or 'value'
    @PutMapping("/update/{field}")
    public ResponseEntity<ChildChoreDTO> updateChildChore(@PathVariable String field, @RequestBody ChildChoreDTO childChoreDTO) {
        ChildChore childChore = childChoreService.updateChildChore(mapToChildChore(childChoreDTO), field);

        return ResponseEntity.status(HttpStatus.OK).body(mapToChildChoreDTO(childChore));
    }

    // Method to delete a chore from a child
    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteChildChore(@RequestBody ChildChoreDTO childChoreDTO) {
        childChoreService.deleteChildChore(mapToChildChore(childChoreDTO));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Mapping methods

    // List<ChildChoreDTO> -> ChildChoreDateDTO
    private ChildChoreDateDTO mapToChildChoreDateDTO(List<ChildChoreDTO> listOfChildChoreDTO) {
        List<ChildChoreDTO> listChoresOfToday = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (ChildChoreDTO childChoreDTO : listOfChildChoreDTO) {
            if (formatter.format(childChoreDTO.getDate()).equals(LocalDate.now().toString())) {
                listChoresOfToday.add(childChoreDTO);
            }
        }

        return new ChildChoreDateDTO(new Date(), listChoresOfToday);
    }

    // ChildChore -> ChildChoreDTO
    private ChildChoreDTO mapToChildChoreDTO(ChildChore childChore) {
        return new ChildChoreDTO(childChore.getChildChoreUuid(), childChore.getChildUuid(), childChore.getChoreUuid(), childChore.getDate(), childChore.getStatus(), childChore.getValue());
    }

    // ChildChoreDTO -> ChildChore
    private ChildChore mapToChildChore(ChildChoreDTO childChoreDTO) {
        ChildChore childChore = new ChildChore();
        childChore.setChildChoreUuid(childChoreDTO.getChildChoreUuid());
        childChore.setChildUuid(childChoreDTO.getChildUuid());
        childChore.setChoreUuid(childChoreDTO.getChoreUuid());
        childChore.setDate(childChoreDTO.getDate());
        childChore.setStatus(childChoreDTO.getStatus());
        childChore.setValue(childChoreDTO.getValue());

        return childChore;
    }

    // List<ChildChore> -> List<ChildChoreDTO>
    private List<ChildChoreDTO> mapToListOfChildChoreDTO(List<ChildChore> listOfChildChore) {
        List<ChildChoreDTO> listOfChildChoreDTO = new ArrayList<>();
        for (ChildChore cc : listOfChildChore) {
            listOfChildChoreDTO.add(mapToChildChoreDTO(cc));
        }

        return listOfChildChoreDTO;
    }
}
