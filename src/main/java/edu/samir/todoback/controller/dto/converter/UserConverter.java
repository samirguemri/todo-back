package edu.samir.todoback.controller.dto.converter;

import edu.samir.todoback.controller.dto.UserDto;
import edu.samir.todoback.persistence.entity.UserEntity;
import edu.samir.todoback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserConverter {

    private final UserService userService;

    public UserEntity dtoToEntity(UserDto dto) {
        UserEntity entity = userService.selectUserByEmail(dto.getEmail());
        if (entity == null) {
            entity = new UserEntity();
            entity.setFullName(dto.getFullName());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
        }
        return entity;
    }

    public UserDto entityToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
