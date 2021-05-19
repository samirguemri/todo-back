package edu.samir.todoback.controller.dto.converter;

import edu.samir.todoback.controller.dto.TodoDto;
import edu.samir.todoback.persistence.entity.TodoEntity;
import edu.samir.todoback.service.TodoService;
import edu.samir.todoback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TodoConverter {

    private final UserService userService;
    private final TodoService todoService;

    @Transactional
    public TodoEntity dtoToEntity(TodoDto dto) {
        TodoEntity todoEntity;
        if (dto.getId() != null) {
            todoEntity = todoService.selectTodoById(dto.getId());
            todoEntity.setTitle(dto.getTitle());
            todoEntity.setValue(dto.getValue());
            todoEntity.setTodoBefore(dto.getTodoBefore());
            todoEntity.setDone(dto.isDone());
        } else {
            todoEntity = new TodoEntity();
            todoEntity.setTitle(dto.getTitle());
            todoEntity.setValue(dto.getValue());
            todoEntity.setUser(userService.selectUserByEmail(dto.getOwner()));
            todoEntity.setTodoBefore(dto.getTodoBefore());
            todoEntity.setRegisteredOn(LocalDateTime.now());
            todoEntity.setDone(false);
        }
        return todoEntity;
    }

    public TodoDto entityToDto(TodoEntity entity) {
        TodoDto dto = new TodoDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setValue(entity.getValue());
        dto.setOwner(entity.getUser().getEmail());
        dto.setTodoBefore(entity.getTodoBefore());
        dto.setDone(entity.isDone());
        return dto;
    }
}
