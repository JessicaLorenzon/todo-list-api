package com.lorenzon.todo_list_api.controllers;

import com.lorenzon.todo_list_api.domain.task.Task;
import com.lorenzon.todo_list_api.domain.task.TaskRequestDTO;
import com.lorenzon.todo_list_api.domain.task.TaskResponseDTO;
import com.lorenzon.todo_list_api.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskService.findAll(pageable);
        Page<TaskResponseDTO> response = tasks.map(x -> new TaskResponseDTO(x));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO body) {
        Task task = taskService.insert(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseDTO(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable String taskId, @RequestBody @Valid TaskRequestDTO body) {
        Task updatedTask = taskService.update(taskId, body);
        return ResponseEntity.ok(new TaskResponseDTO(updatedTask));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
