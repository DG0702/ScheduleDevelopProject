package com.project.scheduledelevopproject.common;

import com.project.scheduledelevopproject.dto.reply.ReplyRequestDto;
import com.project.scheduledelevopproject.dto.reply.ReplyResponseDto;
import com.project.scheduledelevopproject.entity.Reply;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;

public class ReplyMapper {

    // dto -> entity
    public static Reply toEntity(Schedule scheduleId, User userId, ReplyRequestDto dto){
        return new Reply(
                scheduleId,
                userId,
                dto.getContents()
                );
    }

    // entity -> dto
    public static ReplyResponseDto toDto(Reply reply){
        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getSchedule().getScheduleId(),
                reply.getUser().getId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }
}
