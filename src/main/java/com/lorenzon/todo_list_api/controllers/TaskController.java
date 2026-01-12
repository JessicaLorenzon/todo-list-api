package com.lorenzon.todo_list_api.controllers;

import com.lorenzon.todo_list_api.dto.TaskDTO;
import com.lorenzon.todo_list_api.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/todos")
public class TaskController {

    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getAllTasks(Pageable pageable) {
        Page<TaskDTO> tasksDTO = taskService.findAll(pageable);
        return ResponseEntity.ok(tasksDTO);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        taskDTO = taskService.insert(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        taskDTO = taskService.update(taskId, taskDTO);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
