package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScheduleRequest {
    @NotBlank(message = "일정 제목은 비어있을 수 없습니다.")
    @Size(min = 1, max = 255, message = "일정 제목은 1자 이상 255자 이하로 작성되어야 합니다.")
    private String title;

    @NotBlank(message = "일정 내용은 비어있을 수 없습니다.")
    private String content;

    @NotBlank(message = "담당자는 비어있을 수 없습니다.")
    private String manager;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 4, max = 255, message = "비밀번호는 4자 이상 255자 이하로 작성되어야 합니다.")
    private String password;
}