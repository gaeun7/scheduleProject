package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
    private String userId;
    private Long scheduleId;
    private LocalDateTime createdAt;
}