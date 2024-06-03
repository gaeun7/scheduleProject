package com.scheduleproject.scheduleproject.dto;

import com.scheduleproject.scheduleproject.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleCreateRequest {

    private String title;

    private String password;

    private String description;

    private String username;

    public ScheduleCreateRequest(String title, String password, String description, String username) {
        this.title = title;
        this.password = password;
        this.description = description;
        this.username = username;
    }

    public Schedule toEntity() {
        return new Schedule(this.title,
                this.description,
                this.username,
                this.password);
    }
}