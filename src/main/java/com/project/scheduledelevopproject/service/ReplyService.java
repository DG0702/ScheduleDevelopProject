package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.common.ReplyMapper;
import com.project.scheduledelevopproject.dto.reply.ReplyRequestDto;
import com.project.scheduledelevopproject.dto.reply.ReplyResponseDto;
import com.project.scheduledelevopproject.entity.Reply;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.ReplyRepository;
import com.project.scheduledelevopproject.repository.ScheduleRepository;
import com.project.scheduledelevopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ReplyResponseDto save(User user, ReplyRequestDto dto){

        // 로그인 ID 값 가져오기
        Long LoginId = user.getId();

        User userId = userRepository.findById(LoginId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId not found" + LoginId));

        // 일정 ID 값 가져오기
        Schedule scheduleId = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found" + dto.getScheduleId()));

        Reply reply = ReplyMapper.toEntity(scheduleId, userId,dto);

        Reply savedReply = replyRepository.save(reply);

        return ReplyMapper.toDto(savedReply);
    }


    public ReplyResponseDto findById(Long id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reply not Found" + id));

        return ReplyMapper.toDto(reply);
    }

    @Transactional
    public ReplyResponseDto update(Long id , ReplyRequestDto dto){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reply not Found" + id));

        // update
        reply.update(dto.getContents());

        return ReplyMapper.toDto(reply);
    }

    @Transactional
    public void delete(Long id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reply not Found" + id));

        replyRepository.delete(reply);
    }





}
