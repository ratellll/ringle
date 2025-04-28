package com.example.controller;


import com.example.dto.TutorDto;
import com.example.entity.Tutor;
import com.example.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorDto> createTutor(@RequestParam String name) {
        return ResponseEntity.ok(tutorService.createTutor(name));
    }

    @GetMapping
    public ResponseEntity<List<TutorDto>> getAllTutors() {
        return ResponseEntity.ok(tutorService.getAllTutors());
    }

    @GetMapping
    public ResponseEntity<Tutor> getTutorById(@RequestParam Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

}
