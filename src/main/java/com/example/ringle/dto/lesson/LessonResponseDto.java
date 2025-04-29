package com.example.ringle.dto.lesson;

import com.example.ringle.entity.Lesson;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LessonResponseDto {

    @Schema(description = "수업 ID", example = "20")
    private Long id;

    @Schema(description = "튜터 ID", example = "1")
    private Long tutorId;

    @Schema(description = "학생 ID", example = "5")
    private Long studentId;

    @Schema(description = "수업 시작 시간", example = "2025-05-01T10:00:00")
    private LocalDateTime startTime;

    @Schema(description = "수업 종료 시간", example = "2025-05-01T10:30:00")
    private LocalDateTime endTime;

    @Schema(description = "수업 길이 (단위: 분)", example = "30")
    private int duration;

    @Schema(description = "수업 상태", example = "BOOKED")
    private String status;

    public static LessonResponseDto fromEntity(Lesson lesson) {
        return LessonResponseDto.builder()
                .id(lesson.getId())
                .tutorId(lesson.getTutor().getId())
                .studentId(lesson.getStudent().getId())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .duration(lesson.getDuration())
                .status(lesson.getStatus())
                .build();
    }
}