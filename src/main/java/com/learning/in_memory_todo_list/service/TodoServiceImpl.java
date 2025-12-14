package com.learning.in_memory_todo_list.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.learning.in_memory_todo_list.Models.TodoItem;

@Service
public class TodoServiceImpl implements TodoService {

    private List<TodoItem> todoList = new ArrayList<>();

    @Override
    public TodoItem createTodo(TodoItem todoItem) {
        todoList.add(todoItem);
        return null;
    }

    @Override
    public void deleteTodo(Long id) {
        TodoItem matchedTodoItem =  todoList.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));
        
        todoList.remove(matchedTodoItem);
    }

    @Override
    public List<TodoItem> getAllTodos() {
        return todoList;
    }

    @Override
    public TodoItem getTodoById(Long id) {
        TodoItem matchedTodoItem =  todoList.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));
        return matchedTodoItem;
    }

    @Override
    public TodoItem updateTodo(Long id, TodoItem todoItem) {
        TodoItem matchedTodoItem =  todoList.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));

        matchedTodoItem.setTitle(todoItem.getTitle());
        matchedTodoItem.setCompleted(todoItem.getCompleted());
        return matchedTodoItem;
    }
    
}
