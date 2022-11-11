package com.example.backend.service;

import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Kanban {

    private TodoRepo todoRepo;

    public Kanban(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }


    public List<Todo> listTodos() {
        return todoRepo.listTodos();
    }

    public Todo getTodo(String id) {
        return todoRepo.getTodoById(id);
    }

    public void addTodo(Todo task) {

        todoRepo.addTodo(task);
    }

    public void changeTodo(Todo task) {
        todoRepo.changeTodo(task);


    }

    public void deleteTodo(String id) {
        todoRepo.deleteTodo(id);
    }
}
