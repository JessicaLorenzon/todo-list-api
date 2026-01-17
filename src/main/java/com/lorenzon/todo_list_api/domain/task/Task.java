package com.lorenzon.todo_list_api.domain.task;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String description;

    public Task(TaskRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
    }
}
