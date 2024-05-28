package com.scheduleproject.scheduleproject.service;

import com.scheduleproject.scheduleproject.dto.*;
import com.scheduleproject.scheduleproject.entity.Comment;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.exception.ResourceNotFoundException;
import com.scheduleproject.scheduleproject.repository.CommentRepository;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 기존 메서드 생략

    @Transactional
    public CommentDTO createComment(CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없음 " + request.getScheduleId()));

        Comment comment = new Comment(
                request.getContent(),
                request.getUserId(),
                schedule,
                LocalDateTime.now()
        );

        Comment savedComment = commentRepository.save(comment);
        return convertToDTO(savedComment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUserId());
        dto.setScheduleId(comment.getSchedule().getId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
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