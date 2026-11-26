package com.example.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scheduler.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}