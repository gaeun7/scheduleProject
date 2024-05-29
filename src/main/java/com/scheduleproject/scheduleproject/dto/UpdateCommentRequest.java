package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    private Long commentId;
    private Long scheduleId;
    private String content;
    private String userId;
}