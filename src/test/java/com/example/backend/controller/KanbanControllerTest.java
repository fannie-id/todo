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

        String id = todoRepo.addTodo(new Todo("uuuu", Status.OPEN, "999")).id();

        String jsonC = "{\"description\":\"uuuu\",\"status\":\"OPEN\",\"id\":\"" + id + "\"}";
        mvc.perform(get("/api/todo/"+id))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonC));
    }

    @Test
    void addTodo() throws Exception {

        mvc.perform(post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"description":"uuuu","status":"OPEN"}
                           """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{"description": "uuuu","status": "OPEN"}"""));

    }

    @Test
    void changeTodo() throws Exception {
        String id = todoRepo.addTodo(new Todo("uuuu", Status.OPEN, "999")).id();

        String jsonC = "{\"description\":\"aaaa\",\"status\":\"DONE\",\"id\":\"" + id + "\"}";
        mvc.perform(put("/api/todo/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonC))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonC));
    }

    @Test
    void deleteTodo() throws Exception {
        String id = todoRepo.addTodo(new Todo("uuuu", Status.DONE, "999")).id();
        todoRepo.changeTodo(new Todo("uuuu",Status.DONE,id));

        mvc.perform(delete("/api/todo/"+id))
                .andExpect(status().isOk());

    }
}