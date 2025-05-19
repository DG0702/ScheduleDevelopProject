package com.project.scheduledelevopproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "schedule_id")
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;




}
