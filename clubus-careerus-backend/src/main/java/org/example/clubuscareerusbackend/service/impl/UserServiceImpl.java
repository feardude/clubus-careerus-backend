package org.example.clubuscareerusbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clubuscareerusbackend.dto.AuthResponse;
import org.example.clubuscareerusbackend.dto.CreateUserRequest;
import org.example.clubuscareerusbackend.dto.LoginRequest;
import org.example.clubuscareerusbackend.entity.User;
import org.example.clubuscareerusbackend.exception.AlreadyExistsException;
import org.example.clubuscareerusbackend.exception.InvalidLoginException;
import org.example.clubuscareerusbackend.exception.InvalidPasswordException;
import org.example.clubuscareerusbackend.repository.UserRepository;
import org.example.clubuscareerusbackend.security.AppUserDetails;
import org.example.clubuscareerusbackend.security.jwt.JwtUtils;
import org.example.clubuscareerusbackend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{1,}$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");


    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return AuthResponse.builder()
                .token(jwtUtils.generateJwtToken(userDetails))
                .login(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .build();
    }


    @Override
    public void register(CreateUserRequest createUserRequest) {

        if(userRepository.existsByLogin(createUserRequest.getLogin())) {
            throw new AlreadyExistsException("Пользователь с таким логином уже существует!");
        }
        if(userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new AlreadyExistsException("Пользователь с таким Email уже существует!");
        }
            validateLogin(createUserRequest.getLogin());
            validatePassword(createUserRequest.getPassword());


        var user = User.builder()
                .login(createUserRequest.getLogin())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }


    public static void validateLogin(String login) {
        if (!LOGIN_PATTERN.matcher(login).matches()) {
            throw new InvalidLoginException("Некорректный логин. Он должен содержать только латинские буквы, цифры и символ - _ .");
        }
    }

    public static void validatePassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new InvalidPasswordException("Некорректный пароль. В пароле должно быть как минимум 8 знаков, большие и маленькие буквы и цифры .");
        }
    }

}
