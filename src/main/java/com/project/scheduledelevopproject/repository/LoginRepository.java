package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
