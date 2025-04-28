package com.example.dto;


import com.example.entity.AvailableTime;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AvailableTimeDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long tutorId;


    public static AvailableTimeDto fromEntity(AvailableTime availableTime) {
        return AvailableTimeDto.builder()
                .id(availableTime.getId())
                .startTime(availableTime.getStartTime())
                .endTime(availableTime.getEndTime())
                .tutorId(availableTime.getTutor().getId())
                .build();
    }
}
