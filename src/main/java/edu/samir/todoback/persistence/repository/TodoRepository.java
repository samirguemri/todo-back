package edu.samir.todoback.persistence.repository;

import edu.samir.todoback.persistence.entity.TodoEntity;
import edu.samir.todoback.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    Optional<List<TodoEntity>> findByUser(UserEntity user);
}
