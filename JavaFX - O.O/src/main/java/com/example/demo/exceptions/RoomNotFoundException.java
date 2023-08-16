package com.example.demo.exceptions;

public class RoomNotFoundException extends Exception{
    private String number;

    public RoomNotFoundException(String message, String number) {
        super(message);
        this.number = number;
    }
}
