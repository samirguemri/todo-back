package edu.samir.todoback.controller;

import edu.samir.todoback.controller.dto.UserDto;
import edu.samir.todoback.controller.dto.converter.UserConverter;
import edu.samir.todoback.persistence.entity.UserEntity;
import edu.samir.todoback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping(
            path = "/api/user/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        UserEntity entity = userService.insertUser(userConverter.dtoToEntity(userDto));
        if (entity != null) {
            return ResponseEntity.ok().body(userDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserDto> selectUserById(@PathVariable Long id) {
        UserEntity entity = userService.selectUserById(id);
        if (entity != null) {
            return ResponseEntity.ok().body(userConverter.entityToDto(entity));
        }
        return ResponseEntity.badRequest().build();
    }

}
