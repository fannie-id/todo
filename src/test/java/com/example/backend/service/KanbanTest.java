package com.example.backend.service;

import com.example.backend.model.Status;
import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KanbanTest {
    TodoRepo todoRepo = mock(TodoRepo.class);

    Kanban kanban = new Kanban(todoRepo);


    @Test
    void listTodos_shouldReturn_someTodos() {
        List<Todo> expected = new ArrayList<>();
        when(todoRepo.listTodos()).thenReturn(new ArrayList<>());
        List<Todo> result = kanban.listTodos();
        assertEquals(expected, result);
        verify(todoRepo).listTodos();

    }

    @Test
    void getTodoById_should_return_validTodo() {
        String id = "ddd";
        Todo expected = new Todo("abc", Status.OPEN, id);

        when(todoRepo.getTodoById(id)).thenReturn(expected);
        Todo result = kanban.getTodo(id);
        assertEquals(expected, result);
        verify(todoRepo).getTodoById(id);
    }

    @Test
    void addTodo() {
        Todo expected = new Todo("abc", Status.OPEN, "ddd");

        when(todoRepo.addTodo(expected)).thenReturn(expected);
        Todo result = kanban.addTodo(expected);
        assertEquals(expected, result);
        verify(todoRepo).addTodo(expected);
    }

    @Test
    void changeTodo() {
        Todo expected = new Todo("abcd", Status.IN_PROGRESS, "ddd");

        when(todoRepo.changeTodo(expected)).thenReturn(expected);
        Todo result = kanban.changeTodo(expected);
        assertEquals(expected, result);
        verify(todoRepo).changeTodo(expected);
    }

    @Test
    void deleteTodo() {
        Todo expected = new Todo("abcd", Status.DONE, "ddd");

        when(todoRepo.deleteTodo("ddd")).thenReturn(expected);
        Todo result = kanban.deleteTodo("ddd");
        assertEquals(expected, result);
        verify(todoRepo).deleteTodo("ddd");
    }
}