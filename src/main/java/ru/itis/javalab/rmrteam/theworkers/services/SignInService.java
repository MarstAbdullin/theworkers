package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.SignInDto;
import ru.itis.javalab.rmrteam.theworkers.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
