package com.project.scheduledelevopproject.entity;

import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "password", nullable = false)
    private String password;

    // toEntity 생성자
    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    // update
    public void update(String userName, String userEmail){
        if(userName != null){
            this.userName = userName;
        }
        if(userEmail != null){
            this.userEmail = userEmail;
        }
    }

    // entity -> dto
    public UserResponseDto toDto(){
        return new UserResponseDto(userId, userName, userEmail, getCreatedAt(), getUpdatedAt());
    }
}
