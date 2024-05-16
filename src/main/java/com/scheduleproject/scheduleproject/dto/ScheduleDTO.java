package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private LocalDateTime createdAt;
}