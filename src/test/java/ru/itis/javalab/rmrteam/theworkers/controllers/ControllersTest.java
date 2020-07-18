package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {

    @Autowired
    private MockMvc mockMvc;

    String token = "token";

    @Test
    void getStudents() throws Exception {
        mockMvc.perform(get("/getAllStudents")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void getCompanies() throws Exception {
        mockMvc.perform(get("/getAllCompanies")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void getTeachers() throws Exception {
        mockMvc.perform(get("/getAllTeachers")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void getEmptyTags() throws Exception {
        mockMvc.perform(get("/tags")
                .header("Authorization", token))
                .andExpect(status().isBadRequest());
    }
}
