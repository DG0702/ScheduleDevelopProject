package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}
