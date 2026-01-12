package com.lorenzon.todo_list_api.services.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(Long taskId) {
        super("Task with ID " + taskId + " not found");
    }
}
