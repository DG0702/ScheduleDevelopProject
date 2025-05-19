package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.ScheduleRepository;
import com.project.scheduledelevopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;


    public ScheduleResponseDto save(ScheduleRequestDto dto){

        User userId = userRepository.findByIdOrElseThrow(dto.getUserId());

        Schedule schedule = dto.toEntity(userId);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getScheduleId(),
                savedSchedule.getUser().getUserId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }


    public List<ScheduleResponseDto> findAll(){
        List<ScheduleResponseDto> schedules = new ArrayList<>();

        List<Schedule> result = scheduleRepository.findAll();
        for(Schedule schedule : result){
            schedules.add(schedule.toDto());
        }
        return schedules;
    }


    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if(!(schedule.getPassword().equals(dto.getPassword()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong Password");
        }

        // update
        schedule.update(dto.getTitle(), dto.getContents());

        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

    }

    public void delete(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if(!(schedule.getPassword().equals(dto.getPassword()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong Password");
        }

        scheduleRepository.delete(schedule);
    }

}
