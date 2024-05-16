package com.scheduleproject.scheduleproject.service;

import com.scheduleproject.scheduleproject.dto.ScheduleDTO;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.exception.UnauthorizedException;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.scheduleproject.scheduleproject.exception.ResourceNotFoundException;


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

    public ScheduleDTO getSchedule(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            return convertToDTO(scheduleOptional.get());
        } else throw new ResourceNotFoundException("id에 맞는 일정을 찾을 수 없음  " + id);
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc();
        return schedules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ScheduleDTO updateSchedule(Long id, String title, String content, String manager, String password) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id에 맞는 일정을 찾을 수 없음 " + id));
        if (!schedule.getPassword().equals(password)) {
            throw new UnauthorizedException("비밀번호가 틀립니다.");
        }
        schedule.setTitle(title);
        schedule.setContent(content);
        schedule.setManager(manager);
        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return convertToDTO(updatedSchedule);
    }
}