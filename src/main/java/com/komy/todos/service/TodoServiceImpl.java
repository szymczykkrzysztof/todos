package com.komy.todos.service;

import com.komy.todos.entity.Todo;
import com.komy.todos.entity.User;
import com.komy.todos.repository.TodoRepository;
import com.komy.todos.request.TodoRequest;
import com.komy.todos.response.TodoResponse;
import com.komy.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Todo todo = new Todo(
                todoRequest.getTitle(), todoRequest.getDescription(), todoRequest.getPriority(), false, currentUser
        );
        Todo savedTodo = todoRepository.save(todo);
        return convertToTodoResponse(savedTodo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        return todoRepository.findByOwner(currentUser)
                .stream()
                .map(this::convertToTodoResponse)
                .toList();
    }

    @Override
    @Transactional
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> currentTodo = todoRepository.findByIdAndOwner(id, currentUser);
        if (currentTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        currentTodo.get().setComplete(!currentTodo.get().isComplete());
        Todo updatedTodo = todoRepository.save(currentTodo.get());

        return convertToTodoResponse(updatedTodo);
    }

    @Override
    @Transactional
    public void deleteTodo(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> currentTodo = todoRepository.findByIdAndOwner(id, currentUser);
        if (currentTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        todoRepository.delete(currentTodo.get());
    }

    private TodoResponse convertToTodoResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getPriority(),
                todo.isComplete()
        );
    }

}
