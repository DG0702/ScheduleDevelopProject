package com.project.scheduledelevopproject.dto.user;

import com.project.scheduledelevopproject.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank
    @Size(max = 4)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 20)
    private String email;

    @NotBlank
    @Size(max = 10)
    private String password;

    // Dto -> Entity
    public User toEntity(){
       return new User(name,email,password);
    }
}
