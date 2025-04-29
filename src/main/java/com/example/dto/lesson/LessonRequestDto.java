package com.example.dto.lesson;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LessonRequestDto {

    @NotNull
    @Schema(description = "튜터 ID", example = "1")
    private Long tutorId;

    @NotNull
    @Schema(description = "학생 ID", example = "5")
    private Long studentId;

    @NotNull
    @Schema(description = "수업 시작 시간", example = "2025-05-01T10:00:00")
    private LocalDateTime startTime;

    @NotNull
    @Schema(description = "수업 종료 시간", example = "2025-05-01T10:30:00")
    private LocalDateTime endTime;

    @NotNull
    @Min(30)
    @Max(60)
    @Schema(description = "수업 길이 (30 또는 60 분)", example = "30")
    private Integer duration;
}