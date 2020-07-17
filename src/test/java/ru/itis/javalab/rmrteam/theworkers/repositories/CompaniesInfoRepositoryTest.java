package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.CompaniesInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class CompaniesInfoRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompaniesInfoRepository companiesInfoRepository;

    @Test
    @Transactional
    public void findCompanyByIdTest() {

        User user = User.builder()
                .email("email")
                .hash("dws")
                .confirmed(true)
                .build();
        usersRepository.save(user);

        CompanyInfo companyInfo = CompanyInfo.builder()
                .companyName("company")
                .user(user)
                .build();
        companiesInfoRepository.save(companyInfo);

        assertEquals(companyInfo,
                companiesInfoRepository.findById(1L).get());

    }

}
