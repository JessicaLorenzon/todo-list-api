package com.lorenzon.todo_list_api.exceptions;

public class UserForbiddenException extends RuntimeException{

    public UserForbiddenException() {
        super("User forbidden");
    }
}
