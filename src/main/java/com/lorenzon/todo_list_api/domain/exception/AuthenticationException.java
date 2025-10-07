package com.lorenzon.todo_list_api.domain.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {
        super("Error while authenticating");
    }
}
