package com.example.controller;


import com.example.dto.tutor.TutorRequestDto;
import com.example.dto.tutor.TutorResponseDto;
import com.example.entity.Tutor;
import com.example.service.TutorService;
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

    @PostMapping
    public ResponseEntity<TutorResponseDto> createTutor(@Valid @RequestBody TutorRequestDto request) {
        return ResponseEntity.ok(tutorService.createTutor(request));
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDto>> getAllTutors() {
        return ResponseEntity.ok(tutorService.getAllTutors());
    }

    @GetMapping
    public ResponseEntity<Tutor> getTutorById(@RequestParam Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

}
