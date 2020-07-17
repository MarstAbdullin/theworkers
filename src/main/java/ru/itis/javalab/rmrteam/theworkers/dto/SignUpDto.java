package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String email;
    private String password;
    private Role role;
}
