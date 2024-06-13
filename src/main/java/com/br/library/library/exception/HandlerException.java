package com.br.library.library.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException bad) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message(bad.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception , Check the Documentation")
                        .details("Bad Request, check the documentation")
                        .developerMessage(bad.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(EntityNotFoundException entityNotFound) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Entity not found")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception , Check the Documentation")
                        .details("Not Found, check the documentation")
                        .developerMessage(entityNotFound.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetails> handleIllegalArgumentException(IllegalArgumentException illegalArg) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message(illegalArg.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Argument invalid, Check the fields")
                        .details("Argument invalid, check the documentation")
                        .developerMessage(illegalArg.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArg) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Method argument invalid")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Method argument invalid, Check the fields")
                        .details("Method argument invalid, check the documentation")
                        .developerMessage(methodArg.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ExceptionDetails> handleJWTCreationException(JWTCreationException jwtCreationException) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Error generating token")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Error when it was generating token")
                        .details("Error generating token, check the documentation")
                        .developerMessage(jwtCreationException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionDetails> handleJWTVerificationException(JWTVerificationException jwtVerificationException) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Error validating token")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Error when it was verifying and validating token")
                        .details("Error validating token, check the documentation")
                        .developerMessage(jwtVerificationException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }



}

