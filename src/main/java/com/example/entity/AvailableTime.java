package com.example.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public static AvailableTime of(LocalDateTime startTime, LocalDateTime endTime, Tutor tutor) {
        return AvailableTime.builder()
                .startTime(startTime)
                .endTime(endTime)
                .tutor(tutor)
                .build();
    }
}
