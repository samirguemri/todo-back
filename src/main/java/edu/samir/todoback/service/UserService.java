package edu.samir.todoback.service;

import edu.samir.todoback.persistence.entity.UserEntity;
import edu.samir.todoback.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity insertUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity selectUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity selectUserByEmail(String email){
        return userRepository.findUserEntityByEmail(email).orElseThrow(() -> new IllegalArgumentException() );
    }

    public UserEntity updateUser(UserEntity update) {
        return userRepository.save(update);
    }

    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }
}
