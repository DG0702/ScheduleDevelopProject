package com.project.scheduledelevopproject.dto.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyRequestDto {

    @NotBlank
    private Long scheduleId;

    @NotBlank
    @Size(max = 100)
    private String contents;


}
