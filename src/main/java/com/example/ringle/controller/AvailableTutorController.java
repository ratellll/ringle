package com.example.ringle.controller;

import com.example.ringle.dto.availabletutor.AvailableTutorRequestDto;
import com.example.ringle.dto.tutor.TutorResponseDto;
import com.example.ringle.service.AvailableTutorService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "예약 가능한 튜터 조회")
    @PostMapping
    public ResponseEntity<List<TutorResponseDto>> findAvailableTutors(@Valid @RequestBody AvailableTutorRequestDto request) {
        return ResponseEntity.ok(
                availableTutorService.findAvailableTutors(request)
        );
    }
}
