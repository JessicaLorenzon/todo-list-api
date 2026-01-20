package com.lorenzon.todo_list_api.exceptions;

public record ProblemDetailsDTO(Integer status, String title, String detail, String type, String instance) {
}
