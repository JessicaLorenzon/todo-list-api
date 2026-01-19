package com.lorenzon.todo_list_api.domain.task;

import com.lorenzon.todo_list_api.domain.user.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(TaskRequestDTO data, User user) {
        this.title = data.title();
        this.description = data.description();
        this.user = user;
    }
}
