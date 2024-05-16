package com.scheduleproject.scheduleproject;

import com.scheduleproject.scheduleproject.dto.ScheduleDTO;
import com.scheduleproject.scheduleproject.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void testCreateSchedule() {

        String title = "회의";
        String content = "프로젝트 회의";
        String manager = "도레미";
        String password = "password123";

        ScheduleDTO scheduleDTO = scheduleService.createSchedule(title, content, manager, password);

        assertThat(scheduleDTO).isNotNull();
        assertThat(scheduleDTO.getTitle()).isEqualTo(title);
        assertThat(scheduleDTO.getContent()).isEqualTo(content);
        assertThat(scheduleDTO.getManager()).isEqualTo(manager);
        assertThat(scheduleDTO.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}