package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByUserEmailAndPassword(String userEmail, String password);

    default User findByUserEmailAndPassword(String userEmail, String password){
        return findOptionalByUserEmailAndPassword(userEmail,password).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"이메일 또는 비밀번호가 일치하지 않습니다."));
    }
}
