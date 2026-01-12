package com.lorenzon.todo_list_api.services;

import com.lorenzon.todo_list_api.dto.TaskDTO;
import com.lorenzon.todo_list_api.entities.Task;
import com.lorenzon.todo_list_api.repositories.TaskRepository;
import com.lorenzon.todo_list_api.services.exceptions.TaskNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public Page<TaskDTO> findAll(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(x -> new TaskDTO(x));
    }

    @Transactional
    public TaskDTO insert(TaskDTO taskDTO) {
        Task task = new Task();
        copyDtoToEntity(taskDTO, task);
        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Transactional
    public TaskDTO update(Long taskId, TaskDTO taskDTO) {
        Task task = findById(taskId);
        copyDtoToEntity(taskDTO, task);
        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Transactional
    public void delete(Long taskId) {
        Task task = findById(taskId);
        taskRepository.delete(task);
    }

    private void copyDtoToEntity(TaskDTO taskDTO, Task task) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
