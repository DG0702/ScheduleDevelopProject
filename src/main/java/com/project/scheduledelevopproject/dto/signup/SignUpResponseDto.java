package com.project.scheduledelevopproject.dto.signup;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private Long userID;
    private String userName;
    private String userEmail;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
