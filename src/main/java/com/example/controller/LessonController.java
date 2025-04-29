package com.example.controller;


import com.example.dto.lesson.LessonRequestDto;
import com.example.dto.lesson.LessonResponseDto;
import com.example.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Lesson", description = "수업 예약 관련 API")
@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @Operation(summary = "수업 예약")
    @PostMapping
    public ResponseEntity<LessonResponseDto> bookLesson(@Valid @RequestBody LessonRequestDto request) {
        return ResponseEntity.ok(
                lessonService.bookLesson(
                        request.getTutorId(),
                        request.getStudentId(),
                        request.getStartTime(),
                        request.getEndTime(),
                        request.getDuration()
                )
        );
    }
    @Operation(summary = "학생의 수업 목록 조회")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<LessonResponseDto>> getLessonsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(lessonService.getLessonsByStudent(studentId));
    }
}
