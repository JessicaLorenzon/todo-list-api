package com.lorenzon.todo_list_api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @Email
    private String email;

    private String password;
}
