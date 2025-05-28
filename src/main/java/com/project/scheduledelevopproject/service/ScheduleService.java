package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.config.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto, User user){

        // 로그인 id 값 가져오기
        Long loginId = user.getId();

        User userId = userRepository.findByIdOrElseThrow(loginId);

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(dto.getPassword());

        dto.setPassword(encodePassword);

        Schedule schedule = dto.toEntity(userId);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return savedSchedule.toDto();
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
        Schedule schedule = scheduleRepository.findByScheduleIdOrElseThrow(id);

        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findByScheduleIdOrElseThrow(id);

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), schedule.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        // update
        schedule.update(dto.getTitle(), dto.getContents());

        return schedule.toDto();

    }

    @Transactional
    public void delete(Long id, String password){
        Schedule schedule = scheduleRepository.findByScheduleIdOrElseThrow(id);

        boolean isMatch = passwordEncoder.matches(password, schedule.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        scheduleRepository.delete(schedule);
    }

}
