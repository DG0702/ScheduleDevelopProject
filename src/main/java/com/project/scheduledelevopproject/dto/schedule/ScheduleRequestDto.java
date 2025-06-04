package com.project.scheduledelevopproject.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ScheduleRequestDto {

    @NotBlank
    @Size(max = 10)
    private String password;


    @Size(max = 10)
    private String title;


    @Size(max = 100)
    private String contents;

    public void setPassword(@NotBlank @Size(max = 10) String password) {
        this.password = password;
    }
}
