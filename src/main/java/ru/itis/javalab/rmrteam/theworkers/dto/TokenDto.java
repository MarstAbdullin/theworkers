package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class TokenDto {
    private String accessToken;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String role;
}
