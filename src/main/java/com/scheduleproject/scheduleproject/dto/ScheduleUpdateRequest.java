package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateRequest {
    private String title;

    private String description;

    @NotNull
    private String password;

    public ScheduleUpdateRequest(String title, String description, String password) {
        this.title = title;
        this.password = password;
        this.description = description;
    }
}