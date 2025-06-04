package com.project.scheduledelevopproject.common;

import com.project.scheduledelevopproject.dto.user.UserRequestDto;
import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import com.project.scheduledelevopproject.entity.User;

public class UserMapper {

    // Dto -> Entity
    public static User toEntity(UserRequestDto dto){
        return new User(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword());
    }

    // entity -> dto
    public static UserResponseDto toDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
