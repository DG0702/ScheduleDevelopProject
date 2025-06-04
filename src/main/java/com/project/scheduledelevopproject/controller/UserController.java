package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.login.LoginRequestDto;
import com.project.scheduledelevopproject.dto.login.LoginResponseDto;
import com.project.scheduledelevopproject.dto.signup.SignUpRequestDto;
import com.project.scheduledelevopproject.dto.signup.SignUpResponseDto;
import com.project.scheduledelevopproject.dto.user.UserRequestDto;
import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import com.project.scheduledelevopproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @PostMapping("//users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto) {

        UserResponseDto savedUser = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> findAllUser(){

        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto dto) {

        UserResponseDto updatedUser = userService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           @RequestParam String password) {
        userService.delete(id,password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto, HttpServletRequest request) {

        LoginResponseDto login = userService.login(dto);

        HttpSession session = request.getSession();
        session.setAttribute("loginUser",userService.getUser(dto));

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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody @Valid SignUpRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(dto));
    }
}
