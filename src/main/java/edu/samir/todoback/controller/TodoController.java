package edu.samir.todoback.controller;

import edu.samir.todoback.controller.dto.TodoDto;
import edu.samir.todoback.controller.dto.converter.TodoConverter;
import edu.samir.todoback.persistence.entity.TodoEntity;
import edu.samir.todoback.service.TodoService;
import edu.samir.todoback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;
    private final TodoConverter todoConverter;
    private final UserService userService;

    @PostMapping(
            path = "/api/todo/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TodoDto>> addTodo (@RequestBody TodoDto todoDto) {
        TodoEntity todoEntity = todoService.insertTodo(todoConverter.dtoToEntity(todoDto));
        if (todoEntity != null) {
            return selectAllTodos();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/todo/all")
    public ResponseEntity<List<TodoDto>> selectAllTodos() {
        List<TodoEntity> todos = todoService.selectAllTodos();
        if (todos != null) {
            return ResponseEntity.ok(todos.stream().map( todo -> todoConverter.entityToDto(todo)).collect(Collectors.toList()));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/todo/{id}")
    public ResponseEntity<TodoDto> selectTodoById(@PathVariable Long id) {
        TodoEntity todoEntity = todoService.selectTodoById(id);
        if (todoEntity != null) {
            return ResponseEntity.ok(todoConverter.entityToDto(todoEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/todo/user/{email}")
    public ResponseEntity<List<TodoDto>> selectTodosByOwner(@PathVariable String email) {
        List<TodoEntity> todos = todoService.selectTodoByUser(userService.selectUserByEmail(email));
        if (todos != null) {
            return ResponseEntity.ok(todos.stream().map( todo -> todoConverter.entityToDto(todo)).collect(Collectors.toList()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(
            path = "/api/todo/update",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TodoDto>> updateTodo (@RequestBody TodoDto todoDto) {
        TodoEntity todoEntity = todoConverter.dtoToEntity(todoDto);
        todoEntity = todoService.updateTodo(todoEntity);
        if (todoEntity != null) {
            return selectAllTodos();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/api/todo/delete/{id}")
    public ResponseEntity<List<TodoDto>> deleteTodo(@PathVariable Long id) {
        TodoEntity entity = todoService.selectTodoById(id);
        todoService.deleteTodo(entity);
        return selectAllTodos();
    }

}
