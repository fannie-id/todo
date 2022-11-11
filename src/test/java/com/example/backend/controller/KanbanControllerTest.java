package com.example.backend.controller;

import com.example.backend.model.Status;
import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class KanbanControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TodoRepo todoRepo;


    @Test
    void getAllTodos() throws Exception {
        mvc.perform(get("/api/todo")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void getTodo() throws Exception {
        todoRepo.addTodo(new Todo("uuuu", Status.OPEN, "999"));
        mvc.perform(get("/api/todo/999"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"description": "uuuu","status": "OPEN","id": "999"
                        }"""));
    }

    @Test
    void addTodo() throws Exception {
        mvc.perform(post("/api/todo/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"description":"uuuu","status":"OPEN","id":"999"}
                           """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{"description": "uuuu","status": "OPEN","id": "999"}"""));

    }

    @Test
    void changeTodo() {
    }

    @Test
    void deleteTodo() {
    }
}