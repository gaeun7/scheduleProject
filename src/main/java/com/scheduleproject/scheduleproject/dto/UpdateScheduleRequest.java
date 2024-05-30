package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateScheduleRequest {
    @Size(min = 1, max = 255, message = "일정 제목은 1자 이상 255자 이하로 작성되어야 합니다.")
    private String title;

    private String content;

    private String manager;

    @Size(min = 4, max = 255, message = "비밀번호는 4자 이상 255자 이하로 작성되어야 합니다.")
    private String password;
}