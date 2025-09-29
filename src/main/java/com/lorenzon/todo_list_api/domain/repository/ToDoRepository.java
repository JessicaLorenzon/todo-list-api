package com.lorenzon.todo_list_api.domain.repository;

import com.lorenzon.todo_list_api.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<Item, Long> {
}
