package com.komy.todos.service;

import com.komy.todos.request.TodoRequest;
import com.komy.todos.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest todoRequest);

    List<TodoResponse> getAllTodos();

    TodoResponse toggleTodoCompletion(long id);

    void deleteTodo(long id);
}
