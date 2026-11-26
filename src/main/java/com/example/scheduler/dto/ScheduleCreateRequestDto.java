package com.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@NoArgsConstructor
@AllArgsConstructor 
public class ScheduleCreateRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
