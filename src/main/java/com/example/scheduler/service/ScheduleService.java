package com.example.scheduler.service;

import org.springframework.stereotype.Service;

import com.example.scheduler.dto.ScheduleCreateRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional 
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule (requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthor(), requestDto.getPassword());
        
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }
}
