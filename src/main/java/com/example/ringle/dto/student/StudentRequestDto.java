package com.example.ringle.dto.student;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    @Schema(description = "학생 이름", example = "최철수")
    private String name;
}
