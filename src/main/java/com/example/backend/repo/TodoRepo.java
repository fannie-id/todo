package com.example.backend.repo;

import com.example.backend.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepo {
    List<Todo> todos;

    public TodoRepo(List<Todo> todos) {
        this.todos = todos;
    }


    public List<Todo> listTodos() {
        return todos;
    }

    public Todo getTodoById(String id) {
        for (Todo task : todos) {
            if (task.id().equals(id)) {
                return task;
            }
        }
        return null;
    }


    public Todo addTodo(Todo task) {

        String uniqueID = UUID.randomUUID().toString();
        Todo newTask = new Todo(task.description(), task.status(), uniqueID);

        todos.add(newTask);
        return newTask;


    }


    public Todo changeTodo(Todo task) {
        for (Todo todo : todos) {
            if (todo.id().equals(task.id())) {
                todos.set(todos.indexOf(todo),task);
                return task;
            }
        }
        return null;
    }

    public Todo deleteTodo(String id) {

        // must be called before you can call i.remove()
        // Do something
        todos.removeIf(task -> task.id().equals(id));
        return null;

    }
}
