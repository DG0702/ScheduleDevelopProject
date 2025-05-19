package com.project.scheduledelevopproject.dto.schedule;

import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleRequestDto {
    private Long userId;
    private String password;
    private String title;
    private String contents;

    public Schedule toEntity(
            User user){
        return new Schedule(user,password,title,contents);
    }
}
