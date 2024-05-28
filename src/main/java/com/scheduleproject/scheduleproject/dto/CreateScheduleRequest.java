package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String manager;
    private String password;
}