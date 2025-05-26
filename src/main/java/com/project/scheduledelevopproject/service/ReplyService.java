package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.dto.reply.ReplyRequestDto;
import com.project.scheduledelevopproject.dto.reply.ReplyResponseDto;
import com.project.scheduledelevopproject.entity.Reply;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.ReplyRepository;
import com.project.scheduledelevopproject.repository.ScheduleRepository;
import com.project.scheduledelevopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ReplyResponseDto save(User user, ReplyRequestDto dto, Long id){

        // 로그인 ID 값 가져오기
        Long LoginId = user.getId();

        User userId = userRepository.findByIdOrElseThrow(LoginId);

        // 일정 ID 값 가져오기
        Schedule scheduleId = scheduleRepository.findByScheduleIdOrElseThrow(id);

        Reply reply = dto.toEntity(scheduleId, userId);

        Reply savedReply = replyRepository.save(reply);

        return new ReplyResponseDto(
                savedReply.getReplyId(),
                savedReply.getSchedule().getScheduleId(),
                savedReply.getUser().getId(),
                savedReply.getContents(),
                savedReply.getCreatedAt(),
                savedReply.getUpdatedAt()
        );
    }


    public ReplyResponseDto findById(Long id){
        Reply reply = replyRepository.findByReplyIdOrElseThrow(id);

        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getSchedule().getScheduleId(),
                reply.getUser().getId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }

    @Transactional
    public ReplyResponseDto update(Long id , ReplyRequestDto dto){
        Reply reply = replyRepository.findByReplyIdOrElseThrow(id);

        // update
        reply.update(dto.getContents());

        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getSchedule().getScheduleId(),
                reply.getUser().getId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long id){
        Reply reply = replyRepository.findByReplyIdOrElseThrow(id);

        replyRepository.delete(reply);
    }





}
