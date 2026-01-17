package com.lorenzon.todo_list_api.repositories;

import com.lorenzon.todo_list_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
}
