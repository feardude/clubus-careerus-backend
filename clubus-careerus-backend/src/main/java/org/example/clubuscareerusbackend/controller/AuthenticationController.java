package org.example.clubuscareerusbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.clubuscareerusbackend.dto.AuthResponse;
import org.example.clubuscareerusbackend.dto.CreateUserRequest;
import org.example.clubuscareerusbackend.dto.LoginRequest;
import org.example.clubuscareerusbackend.dto.SimpleResponse;
import org.example.clubuscareerusbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.register(createUserRequest);
        return ResponseEntity.ok(new SimpleResponse("Пользователь создан!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }

}
