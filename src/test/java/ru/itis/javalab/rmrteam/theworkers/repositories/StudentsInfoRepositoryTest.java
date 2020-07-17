package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class StudentsInfoRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentsInfoRepository studentsInfoRepository;

    @Test
    @Transactional
    public void findStudentByIdTest() {

        User user = User.builder()
                .email("email")
                .hash("dws")
                .confirmed(true)
                .build();
        usersRepository.save(user);

        StudentInfo studentInfo = StudentInfo.builder()
                .firstName("Ivan")
                .secondName("Ivanov")
                .user(user)
                .build();
        studentsInfoRepository.save(studentInfo);

        assertEquals(studentInfo,
                studentsInfoRepository.findById(1L).get());

    }

}
