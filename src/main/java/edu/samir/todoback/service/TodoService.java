package edu.samir.todoback.service;

import edu.samir.todoback.persistence.entity.TodoEntity;
import edu.samir.todoback.persistence.entity.UserEntity;
import edu.samir.todoback.persistence.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity insertTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    public TodoEntity selectTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public List<TodoEntity> selectTodoByUser(UserEntity user){
        return todoRepository.findByUser(user).orElse(null);
    }

    public TodoEntity updateTodo(TodoEntity update) {
        return todoRepository.save(update);
    }

    public void deleteTodo(TodoEntity todo) {
        todoRepository.delete(todo);
    }

    public List<TodoEntity> selectAllTodos() {
        return todoRepository.findAll();
    }
}
