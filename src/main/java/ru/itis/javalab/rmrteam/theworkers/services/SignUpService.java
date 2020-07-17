package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;

public interface SignUpService {
    void signUp(SignUpDto signUpDto, String confirmLink);
}
