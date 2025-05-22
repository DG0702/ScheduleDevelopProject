package com.project.scheduledelevopproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long Id;
    private String name;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
