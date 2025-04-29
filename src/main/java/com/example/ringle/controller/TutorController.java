package com.example.ringle.controller;


import com.example.ringle.dto.tutor.TutorRequestDto;
import com.example.ringle.dto.tutor.TutorResponseDto;
import com.example.ringle.entity.Tutor;
import com.example.ringle.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tutor", description = "튜터 관련 API")
@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @Operation(summary = "튜터 등록")
    @PostMapping
    public ResponseEntity<TutorResponseDto> createTutor(@Valid @RequestBody TutorRequestDto request) {
        return ResponseEntity.ok(tutorService.createTutor(request));
    }
    @Operation(summary = "모든 튜터 조회")
    @GetMapping
    public ResponseEntity<List<TutorResponseDto>> getAllTutors() {
        return ResponseEntity.ok(tutorService.getAllTutors());
    }
    @Operation(summary = "튜터 단건 조회")
    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

}
