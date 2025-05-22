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


    @PostMapping("/schedule/{id}/reply")
    public ResponseEntity<ReplyResponseDto> createReply(@RequestBody ReplyRequestDto dto, HttpSession session, @PathVariable Long id){
        User user =(User) session.getAttribute("loginUser");

        return ResponseEntity.status(HttpStatus.CREATED).body(replyService.save(user,dto,id));
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<ReplyResponseDto> selectReply(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(replyService.findById(id));
    }

    @PatchMapping("/reply/{id}")
    public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(replyService.update(id, dto));
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id){
        replyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
