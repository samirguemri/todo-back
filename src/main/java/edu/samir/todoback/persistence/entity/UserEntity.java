package edu.samir.todoback.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"todoList"})
@Table(name = "user_account")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TodoEntity> todoList = new ArrayList<>();

}
