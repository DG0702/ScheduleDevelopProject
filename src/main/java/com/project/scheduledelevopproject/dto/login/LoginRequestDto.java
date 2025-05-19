package com.project.scheduledelevopproject.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public class LoginRequestDto {

    private final String userEmail;

    private final String password;
}
