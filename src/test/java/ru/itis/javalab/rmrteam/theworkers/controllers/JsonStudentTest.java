package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@JsonTest
public class JsonStudentTest {

    @Autowired
    private JacksonTester json;


    @Test
    void jsonStudentTest() throws IOException {

        json.forView(StudentInfo.class);
        Set<Resume> resumes = new HashSet<>();
        resumes.add(Resume.builder().city("Kazan").build());
        resumes.add(Resume.builder().city("Moscow").build());

        StudentInfo student = StudentInfo.builder()
                .user(User.builder()
                        .id(1L)
                        .email("e")
                        .build())
                .firstName("Ivan")
                .secondName("Ivanov")
                .resumes(resumes)
                .build();
        String jsonObject = json.write(student).getJson();
        System.out.println(jsonObject);

    }
}
