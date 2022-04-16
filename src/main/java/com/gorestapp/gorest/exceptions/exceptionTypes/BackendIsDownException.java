package com.gorestapp.gorest.exceptions.exceptionTypes;

public class BackendIsDownException extends RuntimeException {
    public BackendIsDownException() {
        super("gorest server is down");
    }
}
