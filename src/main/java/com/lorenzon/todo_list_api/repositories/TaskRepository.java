package com.lorenzon.todo_list_api.repositories;

import com.lorenzon.todo_list_api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
