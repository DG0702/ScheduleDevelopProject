package com.project.scheduledelevopproject.dto.login;

import com.project.scheduledelevopproject.common.EmailRegex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public class LoginRequestDto {

    @NotBlank
    @Pattern(regexp = EmailRegex.emailPattern, message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 20)
    private String userEmail;

    @NotBlank
    @Size(max = 10)
    private String password;
}
