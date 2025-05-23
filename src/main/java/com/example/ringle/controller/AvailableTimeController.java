package com.example.ringle.controller;


import com.example.ringle.dto.availabletime.AvailableTimeRequestDto;
import com.example.ringle.dto.availabletime.AvailableTimeResponseDto;
import com.example.ringle.service.AvailableTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Operation(
            summary = "강의 시간 등록",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "강의 시간 등록 요청 예시",
                                    value = "{\n  \"tutorId\": 1,\n  \"startTime\": \"2025-05-01T10:00:00\",\n  \"endTime\": \"2025-05-01T11:00:00\"\n}"
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "등록된 강의 시간",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = " 강의 시간 등록 요청 예시",
                                    value = "{\n  \"id\": 1,\n  \"startTime\": \"2025-05-01T10:00:00\",\n  \"endTime\": \"2025-05-01T11:00:00\",\n  \"tutorId\": 1\n}"
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<AvailableTimeResponseDto> createAvailableTime(@Valid @RequestBody AvailableTimeRequestDto request) {
        return ResponseEntity.ok(
                availableTimeService.createAvailableTime(
                        request.getTutorId(), request.getStartTime(), request.getEndTime()
                )
        );
    }
    @Operation(summary = "강의 시간 삭제")
    @DeleteMapping("/{availableTimeId}")
    public ResponseEntity<Void> deleteAvailableTime(@PathVariable Long availableTimeId) {
        availableTimeService.deleteAvailableTime(availableTimeId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "튜터별 강의 시간 조회")
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<AvailableTimeResponseDto>> getAvailableTimesByTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(availableTimeService.getAvailableTimesByTutor(tutorId));
    }

    @Operation(summary = "기간과 수업 길이로 가능한 시간대 조회")
    @GetMapping("/search")
    public ResponseEntity<List<AvailableTimeResponseDto>> getAvailableTimesByTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam int duration
    ) {
        return ResponseEntity.ok(
                availableTimeService.findAvailableTimes(start, end, duration)
        );
    }

}
