package com.example.controller;

import com.example.dto.TutorDto;
import com.example.service.AvailableTutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/available-tutors")
@RequiredArgsConstructor
public class AvailableTutorController {

    private final AvailableTutorService availableTutorService;

    @GetMapping
    public ResponseEntity<List<TutorDto>> findAvailableTutors(
            @RequestParam Long studentId,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime,
            @RequestParam int duration) {
        return ResponseEntity.ok(
                availableTutorService.findAvailableTutors(studentId,startDateTime, endDateTime, duration)
        );
    }
}
