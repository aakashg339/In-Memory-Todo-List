package com.learning.in_memory_todo_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.learning.in_memory_todo_list.Models.TodoItem;
import com.learning.in_memory_todo_list.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoItem todoItem) {
        todoRepository.save(todoItem);
    }

    @Override
    public void deleteTodo(Long id) {
        TodoItem matchedTodoItem =  todoRepository.findAll().stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));
        
        todoRepository.delete(matchedTodoItem);
    }

    @Override
    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public TodoItem getTodoById(Long id) {
        TodoItem matchedTodoItem =  todoRepository.findAll().stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));
        return matchedTodoItem;
    }

    @Override
    public TodoItem updateTodo(Long id, TodoItem todoItem) {
        TodoItem matchedTodoItem =  todoRepository.findAll().stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + id + " not present"));

        matchedTodoItem.setTitle(todoItem.getTitle());
        matchedTodoItem.setCompleted(todoItem.getCompleted());
        return matchedTodoItem;
    }
    
}
