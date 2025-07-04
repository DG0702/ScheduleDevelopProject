package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.common.ScheduleMapper;
import com.project.scheduledelevopproject.config.PasswordEncoder;
import com.project.scheduledelevopproject.dto.Page.PageResponseDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.entity.Schedule;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.repository.ScheduleRepository;
import com.project.scheduledelevopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        User userId = userRepository.findById(loginId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId not found" + loginId));

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(dto.getPassword());

        dto.setPassword(encodePassword);

        Schedule schedule = ScheduleMapper.toEntity(dto,userId);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleMapper.toDto(savedSchedule);
    }



    public List<ScheduleResponseDto> findAll(){
        List<ScheduleResponseDto> schedules = new ArrayList<>();

        List<Schedule> result = scheduleRepository.findAll();
        for(Schedule schedule : result){
            schedules.add(ScheduleMapper.toDto(schedule));
        }
        return schedules;
    }


    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found" + id));

        return ScheduleMapper.toDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found" + id));

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), schedule.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        // update
        schedule.update(dto.getTitle(), dto.getContents());

        return ScheduleMapper.toDto(schedule);

    }

    @Transactional
    public void delete(Long id, String password){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found" + id));

        boolean isMatch = passwordEncoder.matches(password, schedule.getPassword());

        if(!isMatch){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");
        }

        scheduleRepository.delete(schedule);
    }

    // page
    public Page<PageResponseDto> getPage(int page, int size){
        Pageable pageable = PageRequest.of(page -1  ,size, Sort.by("updatedAt").descending());

        return scheduleRepository.getPageResponseDto(pageable);
    }

}
