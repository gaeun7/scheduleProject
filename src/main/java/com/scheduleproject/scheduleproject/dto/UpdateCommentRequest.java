package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    @NotNull(message = "댓글 ID는 비어있을 수 없습니다.")
    private Long commentId;

    @NotNull(message = "일정 ID는 비어있을 수 없습니다.")
    private Long scheduleId;

    private String content;

    private String userId;
}