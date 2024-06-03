package com.scheduleproject.scheduleproject.service;

import com.scheduleproject.scheduleproject.dto.CommentCreateRequest;
import com.scheduleproject.scheduleproject.dto.CommentResponse;
import com.scheduleproject.scheduleproject.dto.CommentUpdateRequest;
import com.scheduleproject.scheduleproject.entity.Comment;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.exception.DataNotFoundException;
import com.scheduleproject.scheduleproject.repository.CommentRepository;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponse save(Long scheduleId, CommentCreateRequest request) {

        // DB에 일정이 존재하지 않는 경우
        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        Comment comment = commentRepository.save(new Comment(request.getComment(), request.getUsername(), schedule));
        return CommentResponse.toDto(comment);
    }

    @Transactional
    public CommentResponse update(Long scheduleId, Long commentId, CommentUpdateRequest request) {

        // DB에 일정이 존재하지 않는 경우
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : " + scheduleId));

        // 해당 댓글이 DB에 존재하지 않는 경우
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다."));

        // 사용자가 일치하지 않는 경우
        if (!Objects.equals(comment.getUsername(), request.getUsername())) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        comment.update(request.getComment());
        return CommentResponse.toDto(comment);
    }

    public void delete(Long scheduleId, Long commentId, String username) {

        // DB에 일정이 존재하지 않는 경우
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : " + scheduleId));

        // 해당 댓글이 DB에 존재하지 않는 경우
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다."));

        if (!Objects.equals(comment.getUsername(), username)) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}