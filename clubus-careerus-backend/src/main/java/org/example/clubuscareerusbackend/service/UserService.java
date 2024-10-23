package org.example.clubuscareerusbackend.service;

import org.example.clubuscareerusbackend.dto.AuthResponse;
import org.example.clubuscareerusbackend.dto.CreateUserRequest;
import org.example.clubuscareerusbackend.dto.LoginRequest;

public interface UserService {


    void register(CreateUserRequest createUserRequest);
    AuthResponse authenticateUser(LoginRequest loginRequest);
}
