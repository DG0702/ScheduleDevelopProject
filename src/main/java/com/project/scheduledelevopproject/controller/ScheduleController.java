package com.project.scheduledelevopproject.controller;

import com.project.scheduledelevopproject.dto.Page.PageResponseDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleRequestDto;
import com.project.scheduledelevopproject.dto.schedule.ScheduleResponseDto;
import com.project.scheduledelevopproject.entity.User;
import com.project.scheduledelevopproject.service.PageService;
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

    private final PageService pageService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody @Valid ScheduleRequestDto dto, HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.save(dto,user));
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
                                                      @RequestBody @Valid ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id,dto));
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
        return ResponseEntity.status(HttpStatus.OK).body(pageService.getPage(page,size));
    }
}
