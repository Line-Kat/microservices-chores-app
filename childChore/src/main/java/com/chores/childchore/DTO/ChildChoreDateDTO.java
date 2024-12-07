package com.chores.childchore.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildChoreDateDTO {
    private Date date;
    List<ChildChoreDTO> listOfChildChoreDTO;
}
