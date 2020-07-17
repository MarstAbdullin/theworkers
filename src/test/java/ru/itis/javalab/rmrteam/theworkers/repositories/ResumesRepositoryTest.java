package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.repositories.ResumesRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class ResumesRepositoryTest {

    @Autowired
    private ResumesRepository resumesRepository;

    @Test
    @Transactional
    public void findAllResumes() {
        List<Resume> resumes = new ArrayList<>();

        Resume resume1 = Resume.builder()
                .city("Kazan")
                .email("21@mail.ru")
                .build();
        resumesRepository.save(resume1);
        resumes.add(resume1);

        Resume resume2 = Resume.builder()
                .city("Moscow")
                .email("12@mail.ru")
                .build();
        resumesRepository.save(resume2);
        resumes.add(resume2);

        assertEquals(resumes, resumesRepository.findAll());
    }
}
