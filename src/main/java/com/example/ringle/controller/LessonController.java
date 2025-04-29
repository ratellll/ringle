package com.example.ringle.controller;


import com.example.ringle.dto.lesson.LessonRequestDto;
import com.example.ringle.dto.lesson.LessonResponseDto;
import com.example.ringle.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lesson", description = "수업 예약 관련 API")
@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @Operation(
            summary = "수업 예약",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "수업 예약 요청 예시",
                                    value = "{\n  \"tutorId\": 1,\n  \"studentId\": 2,\n  \"startTime\": \"2025-05-01T10:00:00\",\n  \"endTime\": \"2025-05-01T10:30:00\",\n  \"duration\": 30\n}"
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "예약된 수업",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "수업 예약 응답 예시",
                                    value = "{\n  \"id\": 1,\n  \"tutorId\": 1,\n  \"studentId\": 2,\n  \"startTime\": \"2025-05-01T10:00:00\",\n  \"endTime\": \"2025-05-01T10:30:00\",\n  \"duration\": 30,\n  \"status\": \"BOOKED\"\n}"
                            )
                    )
            )
    )
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
