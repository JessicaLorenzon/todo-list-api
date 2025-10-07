package com.lorenzon.todo_list_api.domain.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(Long postId) {
        super("Post with ID " + postId + " not found");
    }
}
