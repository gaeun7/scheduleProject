package com.scheduleproject.scheduleproject.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateScheduleRequest {
    private String title;
    private String content;
    private String manager;
    private String password;
}