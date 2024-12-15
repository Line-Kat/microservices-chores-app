package com.chores.childchore;

public class ChoreNotFoundException extends RuntimeException {
    public ChoreNotFoundException(String message) {
        super(message);
    }
}
