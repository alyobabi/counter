package com.example.counter.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CounterControllerTest {
    private static MockMvc mockMvc;

    @BeforeAll
    public static void setUp(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void shouldCreateCounterAndReturn201() throws Exception {
        mockMvc.perform(post("/counter/{name}", "name"))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void shouldNotCreateCounterAndReturn404() throws Exception {
        mockMvc.perform(post("/counter/{name}", "name"))
                .andExpect(status().isBadRequest());
    }
}