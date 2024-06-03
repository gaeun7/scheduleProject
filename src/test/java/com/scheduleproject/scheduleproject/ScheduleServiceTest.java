//package com.scheduleproject.scheduleproject;
//
//import com.scheduleproject.scheduleproject.dto.ScheduleResponse;
//import com.scheduleproject.scheduleproject.entity.Schedule;
//import com.scheduleproject.scheduleproject.repository.ScheduleRepository;
//import com.scheduleproject.scheduleproject.service.ScheduleService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ScheduleServiceTest {
//
//    @Mock
//    private ScheduleRepository scheduleRepository;
//
//    @InjectMocks
//    private ScheduleService scheduleService;
//
//    @Test
//    public void testGetAllSchedules() {
//        // Mock 데이터 설정
//        List<Schedule> mockSchedules = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            Schedule schedule = new Schedule("Title " + i, "Content " + i, "Manager " + i, "Password " + i, LocalDateTime.now());
//            schedule.setId((long) i);
//            mockSchedules.add(schedule);
//        }
//        when(scheduleRepository.findAllByOrderByCreatedAtDesc()).thenReturn(mockSchedules);
//
//        // 테스트 수행
//        List<ScheduleResponse> result = scheduleService.getAllSchedules();
//
//        // 결과 확인
//        assertEquals(mockSchedules.size(), result.size());
//        for (int i = 0; i < mockSchedules.size(); i++) {
//            assertEquals(mockSchedules.get(i).getTitle(), result.get(i).getTitle());
//            assertEquals(mockSchedules.get(i).getContent(), result.get(i).getContent());
//            assertEquals(mockSchedules.get(i).getManager(), result.get(i).getManager());
//        }
//    }
//}