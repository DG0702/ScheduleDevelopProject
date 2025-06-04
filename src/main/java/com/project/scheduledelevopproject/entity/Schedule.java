package com.project.scheduledelevopproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    public Schedule(User user, String password, String title, String contents) {
        this.user = user;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    // update
    public void update (String title, String contents){
        Optional.ofNullable(title).ifPresent(t -> this.title = t.isBlank() ? this.title : t);
        Optional.ofNullable(contents).ifPresent(c -> this.contents = c.isBlank() ? this.contents : c);
    }




}
