package com.project.scheduledelevopproject.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleId;
    private Long userId;
    private String title;
    private String contents;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
