package com.lorenzon.todo_list_api.services;

import com.lorenzon.todo_list_api.domain.task.Task;
import com.lorenzon.todo_list_api.domain.task.TaskRequestDTO;
import com.lorenzon.todo_list_api.exceptions.TaskNotFoundException;
import com.lorenzon.todo_list_api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> findAll(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks;
    }

    @Transactional
    public Task insert(TaskRequestDTO dto) {
        Task task = new Task(dto);
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(String taskId, TaskRequestDTO dto) {
        Task task = findById(taskId);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        return task;
    }

    @Transactional
    public void delete(String taskId) {
        Task task = findById(taskId);
        taskRepository.delete(task);
    }

    public Task findById(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
