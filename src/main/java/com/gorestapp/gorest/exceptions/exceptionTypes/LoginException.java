package com.gorestapp.gorest.exceptions.exceptionTypes;

public class LoginException extends RuntimeException {
    public LoginException() {
        super("Error on your User Name/Email or password !!");
    }
}
