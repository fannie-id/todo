package com.example.backend.controller;


import com.example.backend.model.Status;
import com.example.backend.model.Todo;
import com.example.backend.service.Kanban;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KanbanController {
private Kanban kanban;

    public KanbanController(Kanban kanban) {
        this.kanban = kanban;
    }

    @GetMapping("/todo")
    public List<Todo> getAllTodos(){
        return kanban.listTodos();
    }

    @GetMapping(path="/todo/{id}")
    public Todo getTodoById (@PathVariable String id){
        return kanban.getTodo(id);
    }

    @PostMapping(path="/todo")
    public void addTodo( @RequestBody Todo task ){
        kanban.addTodo(task);
    }

    @PutMapping("/todo/{id}")
    public void changeTodo(@RequestBody Todo task){
        kanban.changeTodo(task);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable String id){
        kanban.deleteTodo(id);
    }
}
