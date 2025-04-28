package com.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int duration;

    private String status;

    public static Lesson of(Tutor tutor, Student student, LocalDateTime startTime, LocalDateTime endTime, int duration, String status) {
        return Lesson.builder()
                .tutor(tutor)
                .student(student)
                .startTime(startTime)
                .endTime(endTime)
                .duration(duration)
                .status(status)
                .build();
    }
}
