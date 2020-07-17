package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Tag;

public interface AdminService {

    void deleteTag(Long id);

    void deleteUser(Long id);

    void deleteReview(Long id);

    void deleteResume(Long id);

    void confirmUser(Long id);

    void addTag(Tag tag);
}
