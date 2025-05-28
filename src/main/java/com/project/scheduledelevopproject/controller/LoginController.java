package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.login.LoginRequestDto;
import com.project.scheduledelevopproject.dto.login.LoginResponseDto;
import com.project.scheduledelevopproject.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    public final LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto, HttpServletRequest request) {

        LoginResponseDto login = loginService.login(dto);

        HttpSession session = request.getSession();
        session.setAttribute("loginUser",loginService.getUser(dto));

        return  ResponseEntity.status(HttpStatus.OK).body(login);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
