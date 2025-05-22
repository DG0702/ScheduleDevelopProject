package com.project.scheduledelevopproject.dto.reply;

import com.project.scheduledelevopproject.entity.Reply;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyRequestDto {

    @NotBlank
    @Size(max = 100)
    private String contents;

    public Reply toEntity(Schedule schedule, User user){
        return new Reply(schedule,user,contents);
    }
}
