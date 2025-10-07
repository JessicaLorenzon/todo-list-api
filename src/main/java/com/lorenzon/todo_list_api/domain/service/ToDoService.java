package com.lorenzon.todo_list_api.domain.service;

import com.lorenzon.todo_list_api.domain.exception.ItemNotFoundException;
import com.lorenzon.todo_list_api.domain.model.Item;
import com.lorenzon.todo_list_api.domain.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    @Transactional
    public Item save(Item item) {
        return toDoRepository.save(item);
    }

    @Transactional
    public Item update(Long itemId, Item itemUpdate) {
        Item item = findById(itemId);
        item.setTitle(itemUpdate.getTitle());
        item.setDescription(itemUpdate.getDescription());

        return item;
    }

    @Transactional
    public void delete(Long itemId) {
        Item item = findById(itemId);
        toDoRepository.delete(item);
    }

    public List<Item> findAll() {
        return toDoRepository.findAll();
    }

    public Item findById(Long itemId) {
        return toDoRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }
}
