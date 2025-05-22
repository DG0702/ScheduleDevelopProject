package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByUserEmail(String email){
        return findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"존재하지 않은 이메일 입니다."));
    }
}
