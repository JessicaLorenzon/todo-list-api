package com.lorenzon.todo_list_api.controllers;

import com.lorenzon.todo_list_api.domain.user.AuthenticationDTO;
import com.lorenzon.todo_list_api.domain.user.LoginResponseDTO;
import com.lorenzon.todo_list_api.domain.user.RegisterDTO;
import com.lorenzon.todo_list_api.domain.user.User;
import com.lorenzon.todo_list_api.infra.security.TokenService;
import com.lorenzon.todo_list_api.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        User user = authenticate(data.email(), data.password());
        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        User user = authenticate(data.email(), data.password());
        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    private User authenticate(String email, String password) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        var authentication = authenticationManager.authenticate(authenticationToken);

        return (User) authentication.getPrincipal();
    }
}
