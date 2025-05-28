package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.config.PasswordEncoder;
import com.project.scheduledelevopproject.dto.login.LoginRequestDto;
import com.project.scheduledelevopproject.dto.login.LoginResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto dto){
        
        // 아이디 검증 (DB -> Repository 로직에서 구현)
        User user = loginRepository.findByEmail(dto.getUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"존재하지 않은 이메일 입니다."));
        
        // 비밀번호 검증 (비교 -> Service 로직에서 구현)
        boolean isMatch =  passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(user.getId());
    }

    public User getUser(LoginRequestDto dto){
        return loginRepository.findByEmail(dto.getUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"존재하지 않은 이메일 입니다."));
    }
}
