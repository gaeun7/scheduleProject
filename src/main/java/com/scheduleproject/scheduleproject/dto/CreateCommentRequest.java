package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateCommentRequest {
    private String content;
    private String userId;
    private Long scheduleId;
}