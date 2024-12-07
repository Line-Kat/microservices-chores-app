package com.chores.user.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Balance {
    private UUID childUuid;
    private String childName;
    private UUID parentId;
}
