package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.dto.Page.PageResponseDto;
import com.project.scheduledelevopproject.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT new com.project.scheduledelevopproject.dto.Page.PageResponseDto" +
            "(s.title,s.contents,COUNT (r.replyId) ,s.createdAt,s.updatedAt,u.name) " +
            "FROM Schedule s JOIN s.user u " +
            "LEFT JOIN Reply r " +
            "ON r.schedule = s GROUP BY s.title,s.contents,s.createdAt,s.updatedAt,u.name"
    )
    Page<PageResponseDto> getPageResponseDto(Pageable pageable);
}
