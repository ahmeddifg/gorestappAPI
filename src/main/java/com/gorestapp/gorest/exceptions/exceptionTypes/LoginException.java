package com.gorestapp.gorest.exceptions.exceptionTypes;

public class LoginException extends RuntimeException {
    public LoginException() {
        super("Invalid email or user id  !!");
    }
}
