package com.komy.todos.controller;

import com.komy.todos.request.TodoRequest;
import com.komy.todos.response.TodoResponse;
import com.komy.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Todo REST API Endpoints", description = "Operations for managing user todos")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Create todo for user", description = "Create todo for the signed in user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest) {
        return todoService.createTodo(todoRequest);
    }

    @Operation(summary = "Get all todos for user", description = "Fetch all todo from the signed in user")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<TodoResponse> getAllTodos() {
        return todoService.getAllTodos();
    }

    @Operation(summary = "Update todo for user", description = "Update todo for the signed in user")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse toggleTodoCompletion(@PathVariable("id") @Min(1) Long id) {
        return todoService.toggleTodoCompletion(id);
    }

    @Operation(summary = "Delete todo for user", description = "Delete todo for the signed in user")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("id") @Min(1) Long id) {
        todoService.deleteTodo(id);
    }

}
