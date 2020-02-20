package com.muskan.parking.exception;

public class IncorrectParkingSlot extends Exception {

    public IncorrectParkingSlot(String message) {
        super(message);
    }

    public IncorrectParkingSlot(String message, Throwable cause) {
        super(message, cause);
    }
}
