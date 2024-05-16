package com.scheduleproject.scheduleproject.service;

import com.scheduleproject.scheduleproject.dto.ScheduleDTO;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleDTO createSchedule(String title, String content, String manager, String password) {
        Schedule schedule = new Schedule(title, content, manager, password, LocalDateTime.now());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return convertToDTO(savedSchedule);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setManager(schedule.getManager());
        dto.setCreatedAt(schedule.getCreatedAt());
        return dto;
    }
}