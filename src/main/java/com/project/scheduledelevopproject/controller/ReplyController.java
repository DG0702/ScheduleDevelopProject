package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.reply.ReplyRequestDto;
import com.project.scheduledelevopproject.dto.reply.ReplyResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.service.ReplyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @PostMapping("/reply")
    public ResponseEntity<ReplyResponseDto> createReply(@RequestBody ReplyRequestDto dto, HttpSession session){
        User user =(User) session.getAttribute("loginUser");

        ReplyResponseDto savedReply = replyService.save(user, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReply);
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<ReplyResponseDto> selectReply(@PathVariable Long id){

        ReplyResponseDto replies = replyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }

    @PatchMapping("/reply/{id}")
    public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto dto){

        ReplyResponseDto updatedReply = replyService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReply);
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id){
        replyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
