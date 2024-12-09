package com.chores.childchore.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildChoreDateDTO {
    private LocalDate date;
    List<ChildChoreDTO> listOfChildChoreDTO;
}
