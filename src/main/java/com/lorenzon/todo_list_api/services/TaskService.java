package com.lorenzon.todo_list_api.services;

import com.lorenzon.todo_list_api.dto.TaskDTO;
import com.lorenzon.todo_list_api.entities.Task;
import com.lorenzon.todo_list_api.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
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
        Task task = taskRepository.getReferenceById(taskId);
        copyDtoToEntity(taskDTO, task);
        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Transactional
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private void copyDtoToEntity(TaskDTO taskDTO, Task task) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
    }
}
