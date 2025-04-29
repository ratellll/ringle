package com.example.ringle.dto.student;

import com.example.ringle.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponseDto {

    @Schema(description = "학생 ID", example = "5")
    private Long id;

    @Schema(description = "학생 이름", example = "최철수")
    private String name;

    public static StudentResponseDto fromEntity(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .build();
    }
}
