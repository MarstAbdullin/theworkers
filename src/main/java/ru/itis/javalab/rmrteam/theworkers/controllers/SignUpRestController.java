package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;
import ru.itis.javalab.rmrteam.theworkers.services.SignUpService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SignUpRestController {

    @Autowired
    private SignUpService service;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(value = "/signUp", produces = "text/plain;charset=UTF-8")
    public ResponseEntity registration(@RequestBody SignUpDto dto) {
        try {
            if (dto.getEmail().equals("") || dto.getPassword().equals("")) {
                return ResponseEntity.badRequest().body("Поля не должны быть пустыми");
            }

            service.signUp(dto, UUID.randomUUID().toString());
            return ResponseEntity.ok().body("Регистрация прошла успешно");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Этот email уже зарегистрирован");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}