package com.example.controller;

import com.example.dto.availabletutor.AvailableTutorRequestDto;
import com.example.dto.tutor.TutorResponseDto;
import com.example.service.AvailableTutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Tag(name = "AvailableTutor", description = "예약 가능한 튜터 조회 API")
@RestController
@RequestMapping("/api/available-tutors")
@RequiredArgsConstructor
public class AvailableTutorController {

    private final AvailableTutorService availableTutorService;

    @Operation(summary = "예약 가능한 튜터 조회")
    @PostMapping
    public ResponseEntity<List<TutorResponseDto>> findAvailableTutors(@Valid @RequestBody AvailableTutorRequestDto request) {
        return ResponseEntity.ok(
                availableTutorService.findAvailableTutors(request)
        );
    }
}
