package com.project.scheduledelevopproject.entity;

import com.project.scheduledelevopproject.dto.reply.ReplyResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reply")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String contents;

    public Reply (Schedule schedule, User user, String contents) {
        this.schedule = schedule;
        this.user = user;
        this.contents = contents;
    }

    //update
    public void update (String contents){
       if(contents != null){
           this.contents = contents;
       }
    }

    // entity -> dto
    public ReplyResponseDto toDto(){
        return new ReplyResponseDto(
                replyId,
                schedule.getScheduleId(),
                user.getId(),
                contents,
                getCreatedAt(),
                getUpdatedAt()
        );
    }

}
