package com.example.service;


import com.example.dto.lesson.LessonResponseDto;
import com.example.entity.Lesson;
import com.example.entity.Student;
import com.example.entity.Tutor;
import com.example.repository.LessonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {


    private final LessonRepository lessonRepository;
    private final StudentService studentService;
    private final TutorService tutorService;

    @Transactional
    public LessonResponseDto bookLesson(Long tutorId, Long studentId, LocalDateTime startTime, LocalDateTime endTime, int duration) {
        Tutor tutor = tutorService.findById(tutorId);
        Student student = studentService.findById(studentId);

        validateBooking(tutor, startTime, endTime);

        Lesson lesson = Lesson.builder()
                .tutor(tutor)
                .student(student)
                .startTime(startTime)
                .endTime(endTime)
                .duration(duration)
                .status("BOOKED")
                .build();

        return LessonResponseDto.fromEntity(lessonRepository.save(lesson));
    }

    private void validateBooking(Tutor tutor, LocalDateTime startTime, LocalDateTime endTime) {
        boolean conflict = tutor.getLessons().stream()
                .anyMatch(lesson ->
                        startTime.isBefore(lesson.getEndTime()) &&
                                endTime.isAfter(lesson.getStartTime())
                );
        if (conflict) {
            throw new IllegalStateException("Lesson time conflicts with existing lessons.");
        }
    }

    public List<LessonResponseDto> getLessonsByStudent(Long studentId) {
        return lessonRepository.findAll()
                .stream()
                .filter(lesson -> lesson.getStudent().getId().equals(studentId))
                .map(LessonResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
