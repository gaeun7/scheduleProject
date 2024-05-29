package com.scheduleproject.scheduleproject.service;

import com.scheduleproject.scheduleproject.dto.*;
import com.scheduleproject.scheduleproject.entity.Comment;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.exception.AlreadyDeletedException;
import com.scheduleproject.scheduleproject.exception.ResourceNotFoundException;
import com.scheduleproject.scheduleproject.exception.UnauthorizedException;
import com.scheduleproject.scheduleproject.repository.CommentRepository;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     새로운 일정을 생성합니다.
     @param request 일정 정보가 포함된 요청
     @return 생성된 일정을 DTO로 반환합니다.
     **/
    @Transactional
    public ScheduleDTO createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getManager(),
                request.getPassword(),
                LocalDateTime.now()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return convertToDTO(savedSchedule);
    }

    /**
     ID에 해당하는 일정을 검색합니다.
     @param id 일정의 ID
     @return 검색된 일정을 DTO로 반환합니다.
     @throws ResourceNotFoundException 일정을 찾을 수 없는 경우
     @throws AlreadyDeletedException 일정이 이미 삭제된 경우
     **/
    @Transactional(readOnly = true)
    public ScheduleDTO getSchedule(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            if (schedule.isDeleted()) {
                throw new AlreadyDeletedException("일정이 이미 삭제되었습니다.");
            }
            return convertToDTO(schedule);
        } else {
            throw new ResourceNotFoundException("일정을 찾을 수 없음 " + id);
        }
    }

    /**
     모든 일정을 검색합니다.
     @return 검색된 일정 목록을 DTO 리스트로 반환합니다.
     **/
    @Transactional(readOnly = true)
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc();
        return schedules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
      일정을 업데이트합니다.
     @param id   업데이트할 일정의 ID
     @param request 업데이트할 일정 정보를 포함한 요청
     @return 업데이트된 일정을 DTO로 반환합니다.
     **/
    @Transactional
    public ScheduleDTO updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없음 " + id));
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 틀림");
        }
        if (schedule.isDeleted()) {
            throw new AlreadyDeletedException("일정이 이미 삭제되었습니다.");
        }
        schedule.setTitle(request.getTitle());
        schedule.setContent(request.getContent());
        schedule.setManager(request.getManager());
        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return convertToDTO(updatedSchedule);
    }

    /**
     일정을 삭제합니다.

     @param id  삭제할 일정의 ID
     @param request 삭제를 인증하기 위한 요청
     **/
    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없음 " + id));
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호 틀림");
        }
        if (schedule.isDeleted()) {
            throw new AlreadyDeletedException("일정이 이미 삭제되었습니다.");
        }
        schedule.setDeleted(true);
        scheduleRepository.save(schedule);
    }

    @Transactional
    public CommentDTO createComment(CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없음 " + request.getScheduleId()));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUserId(request.getUserId());
        comment.setSchedule(schedule);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        return convertToDTO(savedComment);
    }

    @Transactional
    public CommentDTO updateComment(UpdateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없음 " + request.getScheduleId()));

        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new ResourceNotFoundException("댓글을 찾을 수 없음 " + request.getCommentId()));

        if (!comment.getUserId().equals(request.getUserId())) {
            throw new UnauthorizedException("댓글 수정 권한이 없음");
        }

        comment.setContent(request.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return convertToDTO(updatedComment);
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

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUserId());
        dto.setScheduleId(comment.getSchedule().getId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}