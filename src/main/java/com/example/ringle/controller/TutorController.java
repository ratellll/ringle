package com.example.ringle.controller;


import com.example.ringle.dto.tutor.TutorRequestDto;
import com.example.ringle.dto.tutor.TutorResponseDto;
import com.example.ringle.entity.Tutor;
import com.example.ringle.service.TutorService;
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

@Tag(name = "Tutor", description = "튜터 관련 API")
@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @Operation(
            summary = "튜터 등록",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "튜터 등록 요청 예시",
                                    value = "{\n  \"name\": \"홍길동\"\n}"
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "등록된 튜터",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "튜터 등록 응답 예시",
                                    value = "{\n  \"id\": 1,\n  \"name\": \"홍길동\"\n}"
                            )
                    )
            )
    )
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
