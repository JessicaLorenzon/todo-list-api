package com.lorenzon.todo_list_api.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException () {
        super("User Not Found");
    }
}
