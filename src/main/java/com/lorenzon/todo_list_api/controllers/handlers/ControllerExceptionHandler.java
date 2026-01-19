package com.lorenzon.todo_list_api.controllers.handlers;

import com.lorenzon.todo_list_api.exceptions.ForbiddenException;
import com.lorenzon.todo_list_api.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleTaskNotFound(TaskNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Task not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://todo-list-api.com/errors/task-not-found"));

        return problemDetail;
    }

    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail forbiddenException(ForbiddenException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setTitle("Forbidden");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://todo-list-api.com/errors/forbidden"));

        return problemDetail;
    }
}
