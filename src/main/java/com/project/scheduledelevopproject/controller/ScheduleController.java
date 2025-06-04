package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.Page.PageResponseDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody @Valid ScheduleRequestDto dto, HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        ScheduleResponseDto savedSchedule = scheduleService.save(dto,user);

        return ResponseEntity.status(HttpStatus.OK).body(savedSchedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){

        List<ScheduleResponseDto> schedules = scheduleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){

        ScheduleResponseDto schedule = scheduleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @RequestBody @Valid ScheduleRequestDto dto){
        ScheduleResponseDto updatedSchedule = scheduleService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSchedule);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestParam String password){
        scheduleService.delete(id,password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // page
    @GetMapping("/page")
    public ResponseEntity<Page<PageResponseDto>> pageSchedule(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<PageResponseDto> pageList = scheduleService.getPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pageList);
    }
}
