package com.example.ringle.controller;


import com.example.ringle.dto.student.StudentRequestDto;
import com.example.ringle.dto.student.StudentResponseDto;
import com.example.ringle.service.StudentService;
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

@Tag(name = "Student", description = "학생 관련 API")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @Operation(
            summary = "학생 등록",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "학생 등록 요청 예시",
                                    value = "{\n  \"name\": \"최현빈\"\n}"
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "등록된 학생",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "학생 등록 응답 예시",
                                    value = "{\n  \"id\": 1,\n  \"name\": \"최현빈\"\n}"
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }
    @Operation(summary = "모든 학생 조회")
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
