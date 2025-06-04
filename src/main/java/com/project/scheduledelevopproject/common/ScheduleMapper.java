package com.project.scheduledelevopproject.common;

import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;

public class ScheduleMapper {


    // dto -> entity
    public static Schedule  toEntity(ScheduleRequestDto dto, User user){
        return new Schedule(
                user,
                dto.getPassword(),
                dto.getTitle(),
                dto.getContents());
    }


    // entity -> dto
    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }
}
