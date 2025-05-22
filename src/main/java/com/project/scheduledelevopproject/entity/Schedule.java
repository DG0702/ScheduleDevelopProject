package com.project.scheduledelevopproject.entity;

import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
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

    public Schedule(User user, String password, String title, String contents) {
        this.user = user;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    // update
    public void update (String title, String contents){
        if(title != null){
            this.title = title;
        }

        if(contents != null){
            this.contents = contents;
        }
    }

    // entity -> dto
    public ScheduleResponseDto toDto(){
        return new ScheduleResponseDto(
                scheduleId,
                user.getId(),
                title, contents,
                getCreatedAt(),
                getUpdatedAt());
    }

}
