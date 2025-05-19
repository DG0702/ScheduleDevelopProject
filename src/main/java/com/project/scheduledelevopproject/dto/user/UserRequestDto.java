package com.project.scheduledelevopproject.dto.user;

import com.project.scheduledelevopproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String userName;
    private String userEmail;
    private String password;

    // Dto -> Entity
    public User toEntity(){
       return new User(userName,userEmail,password);
    }
}
