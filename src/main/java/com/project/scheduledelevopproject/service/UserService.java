package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.config.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;


    public UserResponseDto save(UserRequestDto dto) {

        // 비밀번호 암화화
        String encodePassword = passwordEncoder.encode(dto.getPassword());

        dto.setPassword(encodePassword);

        // dto -> entity
        User user = dto.toEntity();

        User savedUser = userRepository.save(user);

        return new UserResponseDto (
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
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
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findByIdOrElseThrow(id);

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }
        
        // 업데이트
        user.update(dto.getName(),dto.getEmail());

        return new UserResponseDto(
                user.getId(),
                dto.getName(),
                dto.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void delete(Long userId, UserRequestDto dto) {
        User user = userRepository.findByIdOrElseThrow(userId);

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        userRepository.deleteById(userId);
    }


    // 회원가입
    public SignUpResponseDto signup(SignUpRequestDto dto){
        User user = dto.toEntity();
        
        // 비밀번호 암호화
        String encodePassword =passwordEncoder.encode(user.getPassword());

        user.setPassword(encodePassword);

        User signUp = userRepository.save(user);

        return new SignUpResponseDto(
                signUp.getId(),
                signUp.getName(),
                signUp.getEmail(),
                signUp.getCreatedAt(),
                signUp.getUpdatedAt()
        );
    }



}
