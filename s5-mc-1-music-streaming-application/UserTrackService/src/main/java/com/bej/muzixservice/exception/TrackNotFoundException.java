package com.bej.muzixservice.exception;

// Use the@ResponseStatus annotation to set the exception message and status
public class TrackNotFoundException extends Exception {
    public TrackNotFoundException(String s){
        super(s);
    }
}
