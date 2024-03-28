package com.bej.muzixservice.exception;

// Use the@ResponseStatus annotation to set the exception message and status
public class UserNotFoundException extends  Exception{
    public UserNotFoundException (String s){
        super(s);
    }
}
