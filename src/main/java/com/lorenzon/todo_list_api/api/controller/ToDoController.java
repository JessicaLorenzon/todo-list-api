package com.lorenzon.todo_list_api.api.controller;

import com.lorenzon.todo_list_api.api.assembler.ItemAssembler;
import com.lorenzon.todo_list_api.api.model.ItemModel;
import com.lorenzon.todo_list_api.api.model.input.ItemInput;
import com.lorenzon.todo_list_api.domain.model.Item;
import com.lorenzon.todo_list_api.domain.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/todos")
public class ToDoController {

    private ToDoService toDoService;
    private ItemAssembler itemAssembler;

    @PostMapping
    public ResponseEntity<ItemModel> createItem(@Valid @RequestBody ItemInput itemInput) {
        Item item = itemAssembler.toEntity(itemInput);
        ItemModel itemModel = itemAssembler.toModel(toDoService.save(item));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemModel);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemModel> updateItem(@PathVariable Long itemId, @Valid @RequestBody ItemInput itemInput) {
        Item item = itemAssembler.toEntity(itemInput);
        ItemModel itemModel = itemAssembler.toModel(toDoService.update(itemId, item));
        return ResponseEntity.ok(itemModel);
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        toDoService.delete(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ItemModel> getAllItems() {
        return itemAssembler.toCollection(toDoService.findAll());
    }
}
