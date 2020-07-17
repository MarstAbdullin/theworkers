package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Resume;

import java.util.Optional;

public interface ResumeService {
    void updateResume(Resume resume);

    void saveResume(Resume resume, Long id);

    Optional<Resume> getResume(Long id);

    void confirmResume(Long id);
}
