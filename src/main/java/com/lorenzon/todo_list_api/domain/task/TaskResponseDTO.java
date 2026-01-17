package com.lorenzon.todo_list_api.domain.task;

public record TaskResponseDTO(String id, String title, String description) {

    public TaskResponseDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription());
    }
}
