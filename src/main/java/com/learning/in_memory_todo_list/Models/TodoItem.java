package com.learning.in_memory_todo_list.Models;

import lombok.Data;

@Data
public class TodoItem {
    Long id;
    String title;
    Boolean completed;
}
