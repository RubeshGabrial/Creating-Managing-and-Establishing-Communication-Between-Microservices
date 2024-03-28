package com.bej.authentication.exception;


// Use the@ResponseStatus annotation to set the exception message and status
public class UserAlreadyExistsException extends  Exception{
    public UserAlreadyExistsException(String s){
        super(s);
    }
}
