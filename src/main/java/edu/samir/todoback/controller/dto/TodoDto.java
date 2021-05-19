package edu.samir.todoback.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {

    private Long id;
    private String title;
    private String value;
    private LocalDateTime todoBefore;
    private boolean isDone;
    private String owner;

}
