package com.example.ringle.dto.availabletime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AvailableTimeRequestDto {

    @NotNull
    @Schema(description = "튜터 ID", example = "1")
    private Long tutorId;

    @NotNull
    @Schema(description = "강의 시작 시간", example = "2025-05-01T10:00:00")
    private LocalDateTime startTime;

    @NotNull
    @Schema(description = "강의 종료 시간", example = "2025-05-01T11:00:00")
    private LocalDateTime endTime;
}
