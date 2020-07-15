package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return usersRepository.findAllByRole(role);
    }

    @Override
    public List<User> search(String query, String role, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> pageResult = usersRepository.search(query, Role.valueOf(role), pageRequest);
        return pageResult.getContent();
    }


}
