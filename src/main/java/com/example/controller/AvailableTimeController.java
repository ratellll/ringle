package com.example.controller;


import com.example.dto.AvailableTimeDto;
import com.example.dto.TutorDto;
import com.example.entity.AvailableTime;
import com.example.service.AvailableTimeService;
import com.example.service.AvailableTutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/available-time")
@RequiredArgsConstructor
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;
    private final AvailableTutorService availableTutorService;

    @PostMapping
    public ResponseEntity<AvailableTimeDto> createAvailableTime(@RequestParam Long tutorId,
                                                                @RequestParam LocalDateTime startTime,
                                                                @RequestParam LocalDateTime endTime) {
        return ResponseEntity.ok(availableTimeService.createAvailableTime(tutorId, startTime, endTime));
    }

    @DeleteMapping("/{availableTimeId")
    public ResponseEntity<?> deleteAvailableTime(@PathVariable Long availableTimeId) {
        availableTimeService.deleteAvailableTime(availableTimeId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<AvailableTimeDto>> getAvailableTimesByTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(availableTimeService.getAvailableTimesByTutor(tutorId));
    }


}
