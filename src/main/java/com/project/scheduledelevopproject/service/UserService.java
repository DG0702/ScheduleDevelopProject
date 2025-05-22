package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.dto.signup.SignUpRequestDto;
import com.project.scheduledelevopproject.dto.signup.SignUpResponseDto;
import com.project.scheduledelevopproject.dto.user.UserRequestDto;
import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponseDto save(UserRequestDto dto) {
        // dto -> entity
        User user = dto.toEntity();

        User savedUser = userRepository.save(user);

        return new UserResponseDto (
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt());
    }

    public List<UserResponseDto> findAll(){
        List<UserResponseDto> users = new ArrayList<>();

        List<User> result = userRepository.findAll();
        for(User u : result){
            users.add(u.toDto());
        }
        return users;
    }


    public UserResponseDto findById(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);

        return new UserResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!(user.getPassword().equals(dto.getPassword()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }
        
        // 업데이트
        user.update(dto.getName(),dto.getEmail());

        return new UserResponseDto(
                user.getUserId(),
                dto.getName(),
                dto.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void delete(Long userId, UserRequestDto dto) {
        User user = userRepository.findByIdOrElseThrow(userId);

        if(!(user.getPassword().equals(dto.getPassword()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        userRepository.deleteById(userId);
    }


    // 회원가입
    public SignUpResponseDto signup(SignUpRequestDto dto){
        User user = dto.toEntity();

        User signUp = userRepository.save(user);

        return new SignUpResponseDto(
                signUp.getUserId(),
                signUp.getUserName(),
                signUp.getUserEmail(),
                signUp.getCreatedAt(),
                signUp.getUpdatedAt()
        );
    }



}
