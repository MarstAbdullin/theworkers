package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Long> getUserRoleId(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            Role role = user.getRole();
            if (role.equals(Role.COMPANY)){
                return Optional.ofNullable(user.getCompany().getId());
            }
            if (role.equals(Role.STUDENT)){
                return Optional.ofNullable(user.getStudent().getId());
            }
            if (role.equals(Role.TEACHER)){
                return Optional.ofNullable(user.getCompany().getId());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> getRole(Long userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return Optional.ofNullable(user.getRole());
        }
        return Optional.empty();
    }


}
