package com.scheduleproject.scheduleproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public ScheduleDeleteRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}