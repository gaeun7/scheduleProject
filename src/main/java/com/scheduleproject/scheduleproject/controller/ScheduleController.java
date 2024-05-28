package com.scheduleproject.scheduleproject.controller;

import com.scheduleproject.scheduleproject.dto.*;
import com.scheduleproject.scheduleproject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // 기존 메서드 생략

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createComment(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
        request.setScheduleId(id);
        return scheduleService.createComment(request);
    }
}