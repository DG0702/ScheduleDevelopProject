package com.project.scheduledelevopproject.service;

import com.project.scheduledelevopproject.dto.Page.PageResponseDto;
import com.project.scheduledelevopproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {

    private final ScheduleRepository scheduleRepository;

    // page
    public Page<PageResponseDto> getPage(int page, int size){
        Pageable pageable = PageRequest.of(page -1  ,size, Sort.by("updatedAt").descending());

        return scheduleRepository.getPageResponseDto(pageable);
    }
}
