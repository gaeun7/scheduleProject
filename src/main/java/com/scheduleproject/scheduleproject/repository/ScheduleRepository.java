package com.scheduleproject.scheduleproject.repository;

import com.scheduleproject.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}