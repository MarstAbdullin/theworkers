package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto signUpDto, String confirmLink) {
        if (!usersRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .hash(passwordEncoder.encode(signUpDto.getPassword()))
                    .role(signUpDto.getRole())
                    .build();
            usersRepository.save(user);
        } else throw new IllegalArgumentException("Пользователь с таким email уже существует");
    }
}
