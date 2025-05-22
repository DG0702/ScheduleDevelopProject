package com.project.scheduledelevopproject.dto.reply;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReplyResponseDto {
    private Long replyId;
    private Long scheduleId;
    private Long userId;
    private String contents;
    private LocalDate createAt;
    private LocalDate updateAt;
}
