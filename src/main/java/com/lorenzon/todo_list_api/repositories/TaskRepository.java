package com.lorenzon.todo_list_api.repositories;

import com.lorenzon.todo_list_api.domain.task.Task;
import com.lorenzon.todo_list_api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

    Page<Task> findAllByUser(Pageable pageable, User user);
}
