package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private String content;

    private String userId;

    @NotNull(message = "일정 ID는 비어있을 수 없습니다.")
    private Long scheduleId;
}