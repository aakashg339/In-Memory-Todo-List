package com.learning.in_memory_todo_list.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.learning.in_memory_todo_list.Models.TodoItem;
import com.learning.in_memory_todo_list.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController("/api")
public class TodoItemController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<String> createTodo(@RequestBody TodoItem todoItem) {
        todoService.createTodo(todoItem);
        
        return new ResponseEntity<>("Todo item created", HttpStatus.CREATED);
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<Optional<TodoItem>> getTodo(@PathVariable Long id) {
        try {
            TodoItem todoItem = todoService.getTodoById(id);
            return new ResponseEntity<>(Optional.of(todoItem), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(Optional.empty(), e.getStatusCode());
        }
    }

    @GetMapping("todos")
    public ResponseEntity<List<TodoItem>> getTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }
    
    @PutMapping("todos/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        try {
            todoService.updateTodo(id, todoItem);
            return new ResponseEntity<>("Todo item updated", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return new ResponseEntity<>("Todo deleted Successfully", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
