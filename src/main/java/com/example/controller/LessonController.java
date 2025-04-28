package com.example.controller;


import com.example.dto.LessonDto;
import com.example.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @PostMapping
    public ResponseEntity<LessonDto> bookLesson(@RequestParam Long tutorId,
                                                @RequestParam Long studentId,
                                                @RequestParam LocalDateTime startTime,
                                                @RequestParam LocalDateTime endTime,
                                                @RequestParam int duration) {
        return ResponseEntity.ok(lessonService.bookLesson(tutorId, studentId, startTime, endTime, duration));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<LessonDto>> getLessonsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(lessonService.getLessonsByStudent(studentId));
    }
}
