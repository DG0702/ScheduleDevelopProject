package com.project.scheduledelevopproject.dto.schedule;

import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ScheduleRequestDto {

    @NotBlank
    @Size(max = 10)
    private String password;

    @NotBlank
    @Size(max = 10)
    private String title;

    @NotNull
    @Size(max = 100)
    private String contents;

    public Schedule toEntity(
            User user){
        return new Schedule(user,password,title,contents);
    }
}
