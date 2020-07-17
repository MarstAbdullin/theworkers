package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersByRole(Role role);

    List<User> search(String query, String role, Integer page, Integer size);

    Optional<Long> getUserRoleId(Long id);

    Optional<Role> getRole(Long userId);
}
