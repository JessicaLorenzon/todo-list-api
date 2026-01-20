package com.lorenzon.todo_list_api.services;

import com.lorenzon.todo_list_api.domain.task.Task;
import com.lorenzon.todo_list_api.domain.task.TaskRequestDTO;
import com.lorenzon.todo_list_api.domain.user.User;
import com.lorenzon.todo_list_api.exceptions.TaskNotFoundException;
import com.lorenzon.todo_list_api.exceptions.UserForbiddenException;
import com.lorenzon.todo_list_api.repositories.TaskRepository;
import com.lorenzon.todo_list_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Task> findAll(Pageable pageable) {
        User user = (User) userRepository.findByEmail(getLoggedUser());

        return (Page<Task>) taskRepository.findAllByUser(pageable, user);
    }

    @Transactional
    public Task insert(TaskRequestDTO dto) {
        User user = (User) userRepository.findByEmail(getLoggedUser());

        Task task = new Task(dto, user);

        return taskRepository.save(task);
    }

    @Transactional
    public Task update(String taskId, TaskRequestDTO dto) {
        Task task = findById(taskId);

        checkIfUserIsOwner(task);

        task.setTitle(dto.title());
        task.setDescription(dto.description());

        return task;
    }

    @Transactional
    public void delete(String taskId) {
        Task task = findById(taskId);

        checkIfUserIsOwner(task);

        taskRepository.delete(task);
    }

    public Task findById(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private void checkIfUserIsOwner(Task task) {
        User user = (User) userRepository.findByEmail(getLoggedUser());

        if (task.getUser() != user) {
            throw new UserForbiddenException();
        }
    }

    private String getLoggedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
