package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.repositories.TagsRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class TagsRepositoryTest {

    @Autowired
    private TagsRepository tagsRepository;

    @Test
    @Transactional
    public void findTagById() {
        Tag tag = Tag.builder()
                .tag("Java")
                .build();
        tagsRepository.save(tag);

        assertEquals(tag, tagsRepository.findById(1L).get());
    }
}
