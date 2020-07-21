package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void isConfirmed(String confirmLink) {
        Optional<User> personOptional = usersRepository.findByConfirmLink(confirmLink);
        if (personOptional.isPresent()) {
            User user = personOptional.get();
            user.setConfirmed(true);
            usersRepository.confirmed(user.getEmail());
        }
    }

}
