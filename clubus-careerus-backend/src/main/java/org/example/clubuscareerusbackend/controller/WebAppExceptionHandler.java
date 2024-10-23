package org.example.clubuscareerusbackend.controller;

import org.example.clubuscareerusbackend.dto.ErrorResponseBody;
import org.example.clubuscareerusbackend.exception.AlreadyExistsException;
import org.example.clubuscareerusbackend.exception.InvalidLoginException;
import org.example.clubuscareerusbackend.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class WebAppExceptionHandler {


    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseBody> alreadyExistsHandler(AlreadyExistsException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(value = InvalidLoginException.class)
    public ResponseEntity<ErrorResponseBody> invalidLoginHandler(InvalidLoginException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseBody> invalidPasswordHandler(InvalidPasswordException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<ErrorResponseBody> buildResponse(HttpStatus httpStatus, Exception ex, WebRequest webRequest) {
        return ResponseEntity.status(httpStatus)
                .body(ErrorResponseBody.builder()
                        .message(ex.getMessage())
                        .build());
    }

}
