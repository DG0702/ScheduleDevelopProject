package com.project.scheduledelevopproject.repository;

import com.project.scheduledelevopproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findScheduleByIdOrElseThrow(Long scheduleId){
        return findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found" + scheduleId)) ;
    }
}
