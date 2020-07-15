package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersByRole(Role role);

    List<User> search(String query, String role, Integer page, Integer size);
}
