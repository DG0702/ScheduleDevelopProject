package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.signup.SignUpRequestDto;
import com.project.scheduledelevopproject.dto.signup.SignUpResponseDto;
import com.project.scheduledelevopproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody @Valid SignUpRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(dto));
    }
}
