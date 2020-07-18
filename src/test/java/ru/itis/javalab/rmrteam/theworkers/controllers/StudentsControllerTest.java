package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String token = "token";

    @Test
    void getStudents() throws Exception {
        mockMvc.perform(get("/getAllStudents")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }
}
