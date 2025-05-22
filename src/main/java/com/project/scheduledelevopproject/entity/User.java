package com.project.scheduledelevopproject.entity;

import com.project.scheduledelevopproject.dto.user.UserResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long Id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // toEntity 생성자
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // update
    public void update(String name, String email){
        if(name != null){
            this.name = name;
        }
        if(email != null){
            this.email = email;
        }
    }

    // entity -> dto
    public UserResponseDto toDto(){
        return new UserResponseDto(Id, name, email, getCreatedAt(), getUpdatedAt());
    }
}
