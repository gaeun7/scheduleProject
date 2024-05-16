package com.scheduleproject.scheduleproject;

import com.scheduleproject.scheduleproject.dto.ScheduleDTO;
import com.scheduleproject.scheduleproject.entity.Schedule;
import com.scheduleproject.scheduleproject.exception.ResourceNotFoundException;
import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
import com.scheduleproject.scheduleproject.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    public void testGetSchedule_Exists() {
        // Mock 데이터 설정
        Long id = 1L;
        Schedule schedule = new Schedule("Test Title", "Test Content", "Test Manager", "Test Password", LocalDateTime.now());
        schedule.setId(id);
        when(scheduleRepository.findById(id)).thenReturn(Optional.of(schedule));

        // 테스트 수행
        ScheduleDTO result = scheduleService.getSchedule(id);

        // 결과 확인
        assertEquals(schedule.getTitle(), result.getTitle());
        assertEquals(schedule.getContent(), result.getContent());
        assertEquals(schedule.getManager(), result.getManager());
    }

    @Test
    public void testGetSchedule_NotExists() {
        // Mock 데이터 설정 - 일정이 존재하지 않는 경우
        Long id = 1L;
        when(scheduleRepository.findById(id)).thenReturn(Optional.empty());

        // 예외 확인
        assertThrows(ResourceNotFoundException.class, () -> {
            scheduleService.getSchedule(id);
        });
    }
}