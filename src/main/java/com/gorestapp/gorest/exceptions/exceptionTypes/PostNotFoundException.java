package com.gorestapp.gorest.exceptions.exceptionTypes;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("No posts Found");
    }
}
