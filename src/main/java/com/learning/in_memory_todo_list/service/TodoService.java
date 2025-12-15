package com.learning.in_memory_todo_list.service;

import java.util.List;
import com.learning.in_memory_todo_list.Models.TodoItem;

public interface TodoService {
    List<TodoItem> getAllTodos();
    TodoItem getTodoById(Long id);
    void createTodo(TodoItem todoItem);
    TodoItem updateTodo(Long id, TodoItem todoItem);
    void deleteTodo(Long id);
}
