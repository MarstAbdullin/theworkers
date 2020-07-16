package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;

import java.util.Optional;

public interface ResumeService {
    void updateResume(Resume resume);

    void saveResume(Resume resume);

    Optional<Resume> getResume(Long id);
}
