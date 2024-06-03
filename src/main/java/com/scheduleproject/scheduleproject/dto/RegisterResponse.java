package com.scheduleproject.scheduleproject.dto;

import lombok.Getter;

@Getter
public class RegisterResponse {
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }
}