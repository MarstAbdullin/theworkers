package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


}
