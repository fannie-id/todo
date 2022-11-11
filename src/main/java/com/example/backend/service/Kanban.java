package com.example.backend.service;

import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Kanban {

    private final TodoRepo todoRepo;

    public Kanban(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }


    public List<Todo> listTodos() {
        return todoRepo.listTodos();
    }

    public Todo getTodo(String id) {
        return todoRepo.getTodoById(id);
    }

    public Todo addTodo(Todo task) {
       return todoRepo.addTodo(task);
    }

    public Todo changeTodo(Todo task) {
        return todoRepo.changeTodo(task);
    }

    public Todo deleteTodo(String id) {
        return todoRepo.deleteTodo(id);
    }
}
