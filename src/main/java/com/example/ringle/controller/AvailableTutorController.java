package com.example.ringle.controller;

import com.example.ringle.dto.availabletutor.AvailableTutorRequestDto;
import com.example.ringle.dto.tutor.TutorResponseDto;
import com.example.ringle.service.AvailableTutorService;
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


@Tag(name = "AvailableTutor", description = "예약 가능한 튜터 조회 API")
@RestController
@RequestMapping("/api/available-tutors")
@RequiredArgsConstructor
public class AvailableTutorController {

    private final AvailableTutorService availableTutorService;

    @Operation(
            summary = "수업 가능한 튜터 조회",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "가능 튜터 조회 요청 예시",
                                    value = "{\n  \"studentId\": 5,\n  \"startTime\": \"2025-05-01T10:00:00\",\n  \"endTime\": \"2025-05-01T12:00:00\",\n  \"duration\": 30\n}"
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "가능한 튜터 ID 목록",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "가능 튜터 조회 응답 예시",
                                    value = "[1, 2, 3]"
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<List<TutorResponseDto>> findAvailableTutors(@Valid @RequestBody AvailableTutorRequestDto request) {
        return ResponseEntity.ok(
                availableTutorService.findAvailableTutors(request)
        );
    }
}
