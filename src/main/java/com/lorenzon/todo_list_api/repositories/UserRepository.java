package com.lorenzon.todo_list_api.repositories;

import com.lorenzon.todo_list_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
