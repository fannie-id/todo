package com.example.backend.repo;

import com.example.backend.model.Status;
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


    public void addTodo(Todo task) {

        String uniqueID = UUID.randomUUID().toString();


        Todo newTask = new Todo(task.description(), task.status(), uniqueID);

        todos.add(newTask);


    }


    public void changeTodo(Todo task) {
        for (Todo todo : todos) {
            if (todo.id().equals(task.id())) {
                todos.set(todos.indexOf(todo),task);
            }
        }
    }

    public void deleteTodo(String id) {

        Iterator<Todo> i = todos.iterator();
        while (i.hasNext()) {

            Todo task = i.next(); // must be called before you can call i.remove()
            // Do something
            if (task.id().equals(id)) {
                i.remove();
            }
        }

    }
}
