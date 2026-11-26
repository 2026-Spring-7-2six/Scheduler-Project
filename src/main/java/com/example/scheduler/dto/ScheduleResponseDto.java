package com.example.scheduler.dto;

import java.time.LocalDateTime;

import com.example.scheduler.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter 
@JsonPropertyOrder({
    "id",
    "title",
    "content",
    "author",
    "createdAt",
    "updatedAt"
})
public class ScheduleResponseDto {
    private final int id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;   
    
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}