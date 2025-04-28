package com.example.dto;


import com.example.entity.Lesson;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LessonDto {

    private Long id;
    private Long tutorId;
    private Long studentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private String status;

    public static LessonDto fromEntity(Lesson lesson) {
        return LessonDto.builder()
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
