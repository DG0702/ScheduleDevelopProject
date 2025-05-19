package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.dto.login.LoginRequestDto;
import com.project.scheduledelevopproject.dto.login.LoginResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginResponseDto login(LoginRequestDto dto){
        User user = loginRepository.findByUserEmailAndPassword(dto.getUserEmail(),dto.getPassword());

        return new LoginResponseDto(user.getUserId());

    }
}
