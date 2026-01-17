package com.lorenzon.todo_list_api.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String taskId) {
        super("Task with ID " + taskId + " not found");
    }
}
