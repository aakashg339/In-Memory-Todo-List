package com.learning.in_memory_todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.in_memory_todo_list.Models.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}
