package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.TeachersInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class TeachersInfoRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TeachersInfoRepository teachersInfoRepository;

    @Test
    @Transactional
    public void findTeacherByIdTest() {

        User user = User.builder()
                .email("email")
                .hash("dws")
                .confirmed(true)
                .build();
        usersRepository.save(user);

        TeacherInfo teacherInfo = TeacherInfo.builder()
                .firstName("Ivan")
                .secondName("Ivanov")
                .user(user)
                .build();
        teachersInfoRepository.save(teacherInfo);

        assertEquals(teacherInfo,
                teachersInfoRepository.findById(1L).get());

    }

}