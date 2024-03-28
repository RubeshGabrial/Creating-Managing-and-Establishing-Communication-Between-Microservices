package com.bej.authentication.exception;



// Use the@ResponseStatus annotation to set the exception message and status
public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String s){
        super(s);
    }
}
