package com.example.controller;


import com.example.dto.availabletime.AvailableTimeRequestDto;
import com.example.dto.availabletime.AvailableTimeResponseDto;
import com.example.service.AvailableTimeService;
import com.example.service.AvailableTutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "AvailableTime", description = "튜터시간 관련 API")
@RestController
@RequestMapping("/api/available-time")
@RequiredArgsConstructor
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;

    @PostMapping
    public ResponseEntity<AvailableTimeResponseDto> createAvailableTime(@Valid @RequestBody AvailableTimeRequestDto request) {
        return ResponseEntity.ok(
                availableTimeService.createAvailableTime(
                        request.getTutorId(), request.getStartTime(), request.getEndTime()
                )
        );
    }

    @DeleteMapping("/{availableTimeId}")
    public ResponseEntity<Void> deleteAvailableTime(@PathVariable Long availableTimeId) {
        availableTimeService.deleteAvailableTime(availableTimeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<AvailableTimeResponseDto>> getAvailableTimesByTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(availableTimeService.getAvailableTimesByTutor(tutorId));
    }


}
