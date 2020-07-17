package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.*;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.SignInService;
import ru.itis.javalab.rmrteam.theworkers.services.SignUpService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SignUpRestController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private SignInService signInService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(value = "/signUp", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> registration(@RequestBody SignUpDto dto) {
        try {
            if (dto.getEmail().equals("") || dto.getPassword().equals("")) {
                return ResponseEntity.badRequest().body("Поля не должны быть пустыми");
            }

            signUpService.signUp(dto, UUID.randomUUID().toString());
            signInService.signIn(SignInDto.builder().email(dto.getEmail()).password(dto.getPassword()).build());
            return ResponseEntity.ok().body("Регистрация прошла успешно");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Этот email уже зарегистрирован");
        }
    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @PostMapping(value = "/signUp/student", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> registerStudent(@RequestBody StudentInfoDto dto, Authentication authentication) {
            signUpService.registerStudent(dto, ((UserDetailsImpl)authentication.getPrincipal()).getUserId());
            return ResponseEntity.ok().body("Регистрация студента прошла успешно, ждите подтверждения");
    }

    @PreAuthorize(value = "hasRole('COMPANY')")
    @PostMapping(value = "/signUp/company", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyInfoDto dto, Authentication authentication) {
        signUpService.registerCompany(dto, ((UserDetailsImpl)authentication.getPrincipal()).getUserId());
        return ResponseEntity.ok().body("Регистрация компании прошла успешно, ждите подтверждения");
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PostMapping(value = "/signUp/teacher", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherInfoDto dto, Authentication authentication) {
        signUpService.registerTeacher(dto, ((UserDetailsImpl)authentication.getPrincipal()).getUserId());
        return ResponseEntity.ok().body("Регистрация преподавателя прошла успешно, ждите подтверждения");
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