package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.user.UserRequestDto;
import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import com.project.scheduledelevopproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           @RequestParam String password) {
        userService.delete(id,password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
