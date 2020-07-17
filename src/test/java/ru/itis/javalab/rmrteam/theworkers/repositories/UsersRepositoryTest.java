package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class, TestApplicationConfig.class})
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentsInfoRepository studentsInfoRepository;

    @Test
    @Transactional
    public void findUserByEmailTest() {

        User user = User.builder()
                .email("email")
                .hash("dws")
                .confirmed(true)
                .build();
        usersRepository.save(user);
        System.out.println(usersRepository.findAll());

        User found = usersRepository.findByEmail(user.getEmail()).get();

        assertEquals(user, found);
    }

    @Test
    public void test() {
        System.out.println("test");
        assertTrue(true);
    }
}
