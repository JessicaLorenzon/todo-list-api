package com.lorenzon.todo_list_api.api.exceptionhandler;

import com.lorenzon.todo_list_api.domain.exception.AuthenticationException;
import com.lorenzon.todo_list_api.domain.exception.ItemNotFoundException;
import com.lorenzon.todo_list_api.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ProblemDetail handleItemNotFound(ItemNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Item not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://todo-list-api.com/errors/item-not-found"));

        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("User not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://todo-list-api.com/errors/user-not-found"));

        return problemDetail;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthentication(AuthenticationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Error while authenticating");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://todo-list-api.com/errors/error-while-authenticating"));

        return problemDetail;
    }
}
