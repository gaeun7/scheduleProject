package com.scheduleproject.scheduleproject.controller;

import com.scheduleproject.scheduleproject.dto.CreateScheduleRequest;
import com.scheduleproject.scheduleproject.dto.DeleteScheduleRequest;
import com.scheduleproject.scheduleproject.dto.ScheduleDTO;
import com.scheduleproject.scheduleproject.dto.UpdateScheduleRequest;
import com.scheduleproject.scheduleproject.exception.AlreadyDeletedException;
import com.scheduleproject.scheduleproject.exception.ResourceNotFoundException;
import com.scheduleproject.scheduleproject.exception.UnauthorizedException;
import com.scheduleproject.scheduleproject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody CreateScheduleRequest request){
        return scheduleService.createSchedule(request.getTitle(), request.getContent(), request.getManager(), request.getPassword());
    }

    @GetMapping("/{id}")
    public ScheduleDTO getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PutMapping("/{id}")
    public ScheduleDTO updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        return scheduleService.updateSchedule(id, request.getTitle(), request.getContent(), request.getManager(), request.getPassword());
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(id, request.getPassword());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedException(UnauthorizedException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.GONE)
    public String handleAlreadyDeletedException(AlreadyDeletedException ex) {
        return ex.getMessage();
    }
}