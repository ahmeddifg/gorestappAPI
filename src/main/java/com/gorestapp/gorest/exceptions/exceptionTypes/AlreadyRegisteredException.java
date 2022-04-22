package com.gorestapp.gorest.exceptions.exceptionTypes;


public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException() {
        super("There is a user already registered with these information");
    }
}
