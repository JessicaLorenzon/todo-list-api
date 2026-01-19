package com.lorenzon.todo_list_api.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException() {
        super("Forbidden");
    }
}
