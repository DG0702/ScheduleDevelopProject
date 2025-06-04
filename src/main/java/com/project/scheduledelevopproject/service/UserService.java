package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.common.UserMapper;
import com.project.scheduledelevopproject.config.PasswordEncoder;
import com.project.scheduledelevopproject.dto.login.LoginRequestDto;
import com.project.scheduledelevopproject.dto.login.LoginResponseDto;
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




    @Transactional
    public UserResponseDto save(UserRequestDto dto) {

        // 비밀번호 암화화
        String encodePassword = passwordEncoder.encode(dto.getPassword());

        dto.setPassword(encodePassword);

        // dto -> entity
        User user = UserMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return UserMapper.toDto(savedUser);
    }

    public List<UserResponseDto> findAll(){
        List<UserResponseDto> users = new ArrayList<>();

        List<User> result = userRepository.findAll();
        for(User u : result){
            users.add(UserMapper.toDto(u));
        }
        return users;
    }


    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId not found" + userId));

        return UserMapper.toDto(user);
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId not found" + id));

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }
        
        // 업데이트
        user.update(dto.getName(),dto.getEmail());

        return UserMapper.toDto(user);
    }

    @Transactional
    public void delete(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId not found" + userId));

        boolean isMatch = passwordEncoder.matches(password, user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        userRepository.deleteById(userId);
    }


    // 회원가입
    @Transactional
    public SignUpResponseDto signup(SignUpRequestDto dto){
        User user = dto.toEntity();

        if(userRepository.existsByEmail(dto.getUserEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다.");
        }
        
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

    public LoginResponseDto login(LoginRequestDto dto){

        // 아이디 검증 (DB -> Repository 로직에서 구현)
        User user = userRepository.findByEmail(dto.getUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"존재하지 않은 이메일 입니다."));

        // 비밀번호 검증 (비교 -> Service 로직에서 구현)
        boolean isMatch =  passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(user.getId());
    }

    public User getUser(LoginRequestDto dto){
        return userRepository.findByEmail(dto.getUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"존재하지 않은 이메일 입니다."));
    }


}
