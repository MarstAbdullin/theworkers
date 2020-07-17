package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.*;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    ResumesRepository resumesRepository;

    @Autowired
    CertificatesRepository certificatesRepository;

    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void deleteReview(Long id) {
        reviewsRepository.deleteById(id);
    }

    @Override
    public void deleteResume(Long id) {
        resumesRepository.deleteById(id);
    }

    @Override
    public void confirmUser(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setConfirmed(true);
            usersRepository.save(user);
        }
    }

    @Override
    public void addTag(Tag tag) {
        tagsRepository.save(tag);
    }
}
