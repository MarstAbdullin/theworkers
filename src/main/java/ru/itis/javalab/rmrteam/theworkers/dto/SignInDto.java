package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInDto {
    private String email;
    private String password;
}
