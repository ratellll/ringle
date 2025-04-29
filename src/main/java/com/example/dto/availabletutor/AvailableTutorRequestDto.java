package com.example.dto.availabletutor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AvailableTutorRequestDto {

    @NotNull
    @Schema(description = "학생 ID", example = "5")
    private Long studentId;

    @NotNull
    @Schema(description = "조회할 수업 시작 시간", example = "2025-05-01T10:00:00")
    private LocalDateTime startTime;

    @NotNull
    @Schema(description = "조회할 수업 종료 시간", example = "2025-05-01T12:00:00")
    private LocalDateTime endTime;

    @NotNull
    @Min(30)
    @Max(60)
    @Schema(description = "요청 수업 길이 (30 또는 60 분)", example = "30")
    private Integer duration;
}