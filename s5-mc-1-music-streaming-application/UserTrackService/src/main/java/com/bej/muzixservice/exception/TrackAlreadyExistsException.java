package com.bej.muzixservice.exception;


// Use the@ResponseStatus annotation to set the exception message and status
public class TrackAlreadyExistsException extends Exception {
    public TrackAlreadyExistsException(String s){
        super(s);
    }
}
