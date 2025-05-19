package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @RequestBody ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id,dto));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody ScheduleRequestDto dto){
        scheduleService.delete(id,dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
