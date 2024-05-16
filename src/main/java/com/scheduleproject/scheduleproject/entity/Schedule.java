package com.scheduleproject.scheduleproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String manager;
    private String password;
    private LocalDateTime createdAt;

    public Schedule(String title, String content, String manager, String password, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.password = password;
        this.createdAt = createdAt;
    }
}