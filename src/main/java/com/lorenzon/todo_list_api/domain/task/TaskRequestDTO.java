package com.lorenzon.todo_list_api.domain.task;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description
) {
}
