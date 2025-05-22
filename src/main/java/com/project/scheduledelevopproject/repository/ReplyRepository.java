package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    default Reply findByReplyIdOrElseThrow(Long replyId) {
        return findById(replyId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reply not Found" + replyId));
    }
}
